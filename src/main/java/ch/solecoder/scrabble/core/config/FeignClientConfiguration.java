package ch.solecoder.scrabble.core.config;

import ch.solecoder.scrabble.keycloak.client.KeycloakUserClient;
import ch.solecoder.scrabble.keycloak.exception.FeignClientStatusCodeException;
import ch.solecoder.scrabble.net.Urls;
import ch.solecoder.scrabble.net.services.KeycloakUrls;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static feign.FeignException.errorStatus;

@Configuration
@RequiredArgsConstructor
@Import(org.springframework.cloud.openfeign.FeignClientsConfiguration.class)
public class FeignClientConfiguration {

    private final Decoder decoder;
    private final Encoder encoder;
    private final Contract contract;

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (response.status() >= 400) {
                return new FeignClientStatusCodeException(response.status(), response.body());
            }
            return errorStatus(methodKey, response);
        };
    }

    @Bean
    public KeycloakUserClient keycloakUserClient(Urls urls) {
        return createClient(urls.getUrl(KeycloakUrls.KEYCLOAK_AUTH_SERVER), KeycloakUserClient.class);
    }

    private <T> T createClient(String serviceUrl, Class<T> clientClass) {
        return createClient(serviceUrl, clientClass, encoder);
    }

    private <T> T createClient(String serviceUrl, Class<T> clientClass, Encoder encoder) {
        return Feign.builder()
                .client(feignClient()).contract(contract)
                .encoder(encoder).decoder(decoder)
                .errorDecoder(errorDecoder())
                .retryer(Retryer.NEVER_RETRY)
                .target(clientClass, serviceUrl);
    }
}
