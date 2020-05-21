package ch.solecoder.scrabble.libs.keycloak.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessTokenCacheConfiguration {

    @Bean
    public Cache<String, String> accessTokenCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
    }
}
