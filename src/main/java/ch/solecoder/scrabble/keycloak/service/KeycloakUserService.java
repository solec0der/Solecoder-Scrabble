package ch.solecoder.scrabble.keycloak.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.solecoder.scrabble.core.dto.UserDTO;
import ch.solecoder.scrabble.keycloak.client.KeycloakUserClient;
import ch.solecoder.scrabble.keycloak.converter.UserConverter;
import ch.solecoder.scrabble.keycloak.model.User;
import ch.solecoder.scrabble.keycloak.repository.UserRepository;
import lombok.RequiredArgsConstructor;

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

        for (UserDTO keycloakUserDTO : keycloakUsers) {
            coreUsers
                    .stream()
                    .filter(u -> u.getId().equals(keycloakUserDTO.getId()))
                    .findFirst().ifPresentOrElse(
                    coreUserDTO -> updateExistingCoreUser(coreUserDTO, keycloakUserDTO),
                    () -> createNewCoreUserFromKeycloakUser(keycloakUserDTO));
        }
    }

    private void updateExistingCoreUser(UserDTO coreUserDTO, UserDTO keycloakUserDTO) {
        if (coreUserDTO.equals(keycloakUserDTO)) {
            return;
        }

        User user = userRepository.findById(coreUserDTO.getId()).orElse(null);

        if (user != null) {
            user = user.toBuilder()
                    .username(keycloakUserDTO.getUsername())
                    .enabled(keycloakUserDTO.isEnabled())
                    .totp(keycloakUserDTO.isEmailVerified())
                    .emailVerified(keycloakUserDTO.isEmailVerified())
                    .notBefore(keycloakUserDTO.getNotBefore())
                    .build();

            userRepository.save(user);
        }
    }

    private void createNewCoreUserFromKeycloakUser(UserDTO keycloakUserDTO) {
        User user = User.builder()
                .id(keycloakUserDTO.getId())
                .createdTimestamp(keycloakUserDTO.getCreatedTimestamp())
                .username(keycloakUserDTO.getUsername())
                .enabled(keycloakUserDTO.isEnabled())
                .totp(keycloakUserDTO.isEmailVerified())
                .emailVerified(keycloakUserDTO.isEmailVerified())
                .notBefore(keycloakUserDTO.getNotBefore())
                .build();

        userRepository.save(user);
    }
}
