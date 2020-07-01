package ch.solecoder.scrabble.keycloak.client;

import ch.solecoder.scrabble.keycloak.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface KeycloakUserClient {

    @GetMapping("/admin/realms/{realm}/users")
    List<UserDTO> getUsersByRealm(@PathVariable("realm") String realm);

}
