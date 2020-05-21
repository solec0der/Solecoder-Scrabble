package ch.solecoder.scrabble.libs.keycloak.repository;

import ch.solecoder.scrabble.libs.keycloak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
