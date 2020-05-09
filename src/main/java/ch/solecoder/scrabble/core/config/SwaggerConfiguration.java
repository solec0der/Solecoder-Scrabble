package ch.solecoder.scrabble.core.config;

import com.google.common.collect.Lists;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${keycloak.auth-server-url:}")
    private String authServerUrl;

    @Value("${keycloak.realm:}")
    private String realm;

    @Value("${authentication.frontend-client-id:}")
    private String frontendClientId;

    @Value("${info.app.version:}")
    private String serviceVersion;

    @Value("${info.app.contact.name:}")
    private String contactName;

    @Value("${info.app.contact.email:}")
    private String contactEmail;

    @Value("${info.app.contact.url:}")
    private String contactUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ch.solecoder"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Solecoder Scrabble Score RESTful Service")
                .version(serviceVersion)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }

    @Bean
    public SecurityConfiguration securityConfiguration(Docket docket) {
        docket
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));

        return SecurityConfigurationBuilder.builder()
                .clientId(frontendClientId)
                .realm(realm)
                .clientSecret("")
                .appName("")
                .scopeSeparator("")
                .additionalQueryStringParams(Collections.emptyMap())
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new ImplicitGrant(
                new LoginEndpoint(
                        this.authServerUrl + "/realms/" + this.realm + "/protocol/openid-connect/auth"
                ), OAuth2Constants.ACCESS_TOKEN
        );
        return new OAuth("oauth2", Collections.emptyList(), Lists.newArrayList(grantType));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Lists.newArrayList(new SecurityReference("oauth2", new AuthorizationScope[0])))
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
}
