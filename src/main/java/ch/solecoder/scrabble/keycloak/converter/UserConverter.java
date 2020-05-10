package ch.solecoder.scrabble.keycloak.converter;

import ch.solecoder.scrabble.keycloak.model.UserDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static ch.solecoder.scrabble.core.dto.UserDTO convertToCoreUserDTO(UserDTO userDTO) {
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
