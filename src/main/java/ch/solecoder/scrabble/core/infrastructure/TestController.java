package ch.solecoder.scrabble.core.infrastructure;

import ch.solecoder.scrabble.keycloak.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class TestController {

    private final KeycloakUserService keycloakUserService;

    @GetMapping("/test")
    public void test() {
         keycloakUserService.synchronizeKeycloakUsersWithCoreUsers();
    }
}
