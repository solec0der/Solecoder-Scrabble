package ch.solecoder.scrabble.libs.keycloak.service;

import ch.solecoder.scrabble.core.dto.UserDTO;
import ch.solecoder.scrabble.libs.keycloak.client.KeycloakUserClient;
import ch.solecoder.scrabble.libs.keycloak.converter.UserConverter;
import ch.solecoder.scrabble.libs.keycloak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    @Value("${keycloak.realm}")
    private String realm;

    private final KeycloakUserClient keycloakUserClient;
    private final UserRepository userRepository;

    public void synchronizeKeycloakUsersWithCoreUsers() {
        List<UserDTO> coreUsers = userRepository.findAll().stream()
                .map(UserConverter::convertToDTO)
                .collect(Collectors.toList());

        List<UserDTO> keycloakUsers = keycloakUserClient.getUsersByRealm(realm).stream()
                .map(UserConverter::convertKeycloakToCoreUserDTO)
                .collect(Collectors.toList());

        for (UserDTO userDTO : coreUsers) {
            UserDTO foundKeycloakUser = keycloakUsers
                    .stream()
                    .filter(u -> u.getId().equals(userDTO.getId()))
                    .findFirst()
                    .orElse(null);
        }
    }
}
