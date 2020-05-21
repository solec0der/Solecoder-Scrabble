package ch.solecoder.scrabble.libs.keycloak.security;

import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

public interface KeycloakRestTemplateFactory extends Supplier<RestTemplate> {
}