package ch.solecoder.scrabble.keycloak.converter;

import ch.solecoder.scrabble.keycloak.dto.UserDTO;
import ch.solecoder.scrabble.keycloak.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static ch.solecoder.scrabble.core.dto.UserDTO convertToDTO(User user) {
        return user == null ? null :
                ch.solecoder.scrabble.core.dto.UserDTO.builder()
                        .id(user.getId())
                        .createdTimestamp(user.getCreatedTimestamp())
                        .username(user.getUsername())
                        .enabled(user.isEnabled())
                        .totp(user.isTotp())
                        .emailVerified(user.isEmailVerified())
                        .notBefore(user.getNotBefore())
                        .build();
    }

    public static ch.solecoder.scrabble.core.dto.UserDTO convertKeycloakToCoreUserDTO(UserDTO userDTO) {
        return userDTO == null ? null :
                ch.solecoder.scrabble.core.dto.UserDTO.builder()
                        .id(userDTO.getId())
                        .createdTimestamp(userDTO.getCreatedTimestamp())
                        .username(userDTO.getUsername())
                        .enabled(userDTO.isEnabled())
                        .totp(userDTO.isTotp())
                        .emailVerified(userDTO.isEmailVerified())
                        .notBefore(userDTO.getNotBefore())
                        .build();
    }
}
