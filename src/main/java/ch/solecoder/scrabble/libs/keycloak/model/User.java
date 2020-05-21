package ch.solecoder.scrabble.libs.keycloak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "user", schema = "scrabble")
@NoArgsConstructor(force = true)
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "created_timestamp")
    private long createdTimestamp;

    @NotNull
    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "totp")
    private boolean totp;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @NotNull
    @Column(name = "not_before")
    private long notBefore;

}
