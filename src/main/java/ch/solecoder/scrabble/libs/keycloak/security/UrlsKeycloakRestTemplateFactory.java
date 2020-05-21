package ch.solecoder.scrabble.libs.keycloak.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UrlsKeycloakRestTemplateFactory implements KeycloakRestTemplateFactory {

    @Qualifier("restTemplateBuilder")
    private final RestTemplateBuilder builder;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Override
    public RestTemplate get() {
        return builder.rootUri(keycloakUrl).build();
    }
}
