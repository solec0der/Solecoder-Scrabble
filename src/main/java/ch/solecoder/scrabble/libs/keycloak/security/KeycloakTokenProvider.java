package ch.solecoder.scrabble.libs.keycloak.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.constants.ServiceUrlConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.CharBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.keycloak.OAuth2Constants.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
public class KeycloakTokenProvider {

    private final String tokenPath;
    private final KeycloakRestTemplateFactory restTemplateFactory;

    private static final String MASTER_REALM = "master";

    public KeycloakTokenProvider(KeycloakRestTemplateFactory restTemplateFactory) {
        this.tokenPath = ServiceUrlConstants.TOKEN_PATH.replace("{realm-name}", MASTER_REALM);
        this.restTemplateFactory = restTemplateFactory;
    }

    /**
     * The client secret may be null if the client defined in keycloak does not have a secret defined.  In this
     * case basic auth will not be used.  For an example of a client that does not need a clientSecret see the itests
     * client on CI and CT environments.  For am example of a client that does, see polypoint-tpms-webapp
     */
    public OAuth2Credentials getUserAccessToken(String clientId, @Nullable String clientSecret, String username, char[] password) {
        if (clientId == null || username == null || isEmpty(password)) {
            throw new OpenIdTokenObtainingException("No clientId, username or password provided for token obtaining, clientId: " +
                    clientId + ", username is null? " + (username == null) + ", password is null? " + isEmpty(password));
        }

        return new TokenAccessor(null, 0).newInstanceWithValidToken(clientId, clientSecret, username, password);
    }

    public boolean isAccessTokenExpired(String accessToken) {
        if (accessToken == null)
            return true;

        DecodedJWT decodedJWT = com.auth0.jwt.JWT.decode(accessToken);

        LocalDateTime expiresAt = LocalDateTime.ofInstant(
                decodedJWT.getExpiresAt().toInstant(),
                ZoneId.systemDefault());

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());

        return currentDateTime.isAfter(expiresAt);
    }

    private class TokenAccessor {
        @Getter
        private final char[] tokenString;
        private final Instant expirationTime;

        private TokenAccessor(char[] tokenString, int expiresInSeconds) {
            this.tokenString = tokenString;
            expirationTime = Instant.now().plusSeconds(expiresInSeconds - 10L);
        }

        TokenAccessor refreshIfNeeded(String clientId, String secret) {
            if (needsRefresh()) {
                log.debug("Keycloak access token not in cache for client: " + clientId + "...");
                return newInstanceWithValidToken(clientId, secret);
            } else {
                return this;
            }
        }

        private boolean needsRefresh() {
            return tokenString == null || !Instant.now().isBefore(expirationTime);
        }

        private TokenAccessor newInstanceWithValidToken(String clientId, String secret) {
            HttpEntity<?> request = prepareRequestWithClientCredentials(clientId, secret);
            try {
                ResponseEntity<OAuth2Credentials> response = restTemplateFactory.get().exchange(tokenPath, HttpMethod.POST,
                        request, OAuth2Credentials.class);
                String accessToken = response.getBody().getAccessToken();
                int expiresIn = response.getBody().getExpiresIn();
                return new TokenAccessor(accessToken.toCharArray(), expiresIn);
            } catch (HttpClientErrorException e) {
                String partOfSecret = secret.substring(secret.length() - 3);
                log.error("Error attempting to obtain token for " + clientId + " using secret " + partOfSecret);
                throw new OpenIdTokenObtainingException("Error attempting to obtain token for " + clientId + " using secret " + partOfSecret, e);
            }
        }

        private OAuth2Credentials newInstanceWithValidToken(String clientId, String clientSecret, String username, char[] password) {
            HttpEntity<byte[]> request = prepareRequestWithUserCredentials(clientId, clientSecret, username, password);
            try {
                ResponseEntity<OAuth2Credentials> responseEntity = restTemplateFactory.get().exchange(tokenPath, HttpMethod.POST, request, OAuth2Credentials.class);
                Arrays.fill(request.getBody(), Byte.MIN_VALUE);

                return responseEntity.getBody();
            } catch (HttpClientErrorException e) {
                log.error("Error attempting to obtain token for " + clientId + " with password grant");
                throw new OpenIdTokenObtainingException("Error attempting to obtain token for " + clientId, e);
            } catch (Exception e) {
                log.error("Unexpected error obtaining keycloak token: ", e);
                throw e;
            }
        }

        private HttpEntity<?> prepareRequestWithClientCredentials(String clientId, String secret) {
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add(GRANT_TYPE, CLIENT_CREDENTIALS);
            HttpHeaders headers = createHeadersWithBasicAuthorization(clientId, secret);
            return new HttpEntity<>(requestBody, headers);
        }

        private HttpHeaders createHeadersWithBasicAuthorization(String clientId, String secret) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_FORM_URLENCODED);
            if (secret != null) {
                String encodedCredentials = Base64Utils.encodeToString((clientId + ":" + secret).getBytes(UTF_8));
                headers.set(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
            }
            return headers;
        }

        private HttpEntity<byte[]> prepareRequestWithUserCredentials(String clientId, String clientSecret, String username, char[] password) {
            StringBuilder requestBody = new StringBuilder();
            ExtendedURLCodec urlCodec = ExtendedURLCodec.INSTANCE;
            requestBody.append(GRANT_TYPE).append("=").append(PASSWORD)
                    .append("&").append(CLIENT_ID).append("=").append(urlCodec.encode(clientId))
                    .append("&").append("username").append("=").append(urlCodec.encode(username))
                    .append("&").append("password").append("=").append(urlCodec.encodeChars(password));

            HttpHeaders headers = createHeadersWithBasicAuthorization(clientId, clientSecret);
            return new HttpEntity<>(UTF_8.encode(CharBuffer.wrap(requestBody)).array(), headers);
        }
    }

    static class OpenIdTokenObtainingException extends AuthenticationException {
        private static final long serialVersionUID = -9073701949811687717L;

        OpenIdTokenObtainingException(String msg, Throwable t) {
            super(msg, t);
        }

        OpenIdTokenObtainingException(String msg) {
            super(msg);
        }
    }
}
