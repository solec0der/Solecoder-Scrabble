package ch.solecoder.scrabble.core.config;

import ch.solecoder.scrabble.libs.keycloak.security.KeycloakTokenProvider;
import ch.solecoder.scrabble.libs.keycloak.security.OAuth2Credentials;
import com.google.common.cache.Cache;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakTokenInterceptor implements RequestInterceptor {

    @Value("${keycloak-admin.client-id}")
    private String keycloakAdminClientId;

    @Value("${keycloak-admin.username}")
    private String keycloakAdminUsername;

    @Value("${keycloak-admin.password}")
    private String keycloakAdminPassword;

    private final Cache<String, String> accessTokenCache;
    private final KeycloakTokenProvider keycloakTokenProvider;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = accessTokenCache.asMap().get(keycloakAdminClientId + "#" + keycloakAdminUsername);

        if (keycloakTokenProvider.isAccessTokenExpired(accessToken)) {
            OAuth2Credentials oAuth2Credentials = keycloakTokenProvider.getUserAccessToken(
                    keycloakAdminClientId,
                    Strings.EMPTY,
                    keycloakAdminUsername,
                    keycloakAdminPassword.toCharArray()
            );
            accessToken = oAuth2Credentials.getAccessToken();
            accessTokenCache.put(keycloakAdminClientId + "#" + keycloakAdminUsername, accessToken);
        }
        requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }
}
