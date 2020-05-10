package ch.solecoder.scrabble.keycloak.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String id;
    private long createdTimestamp;
    private String username;
    private boolean enabled;
    private boolean totp;
    private boolean emailVerified;
    private long notBefore;

}
