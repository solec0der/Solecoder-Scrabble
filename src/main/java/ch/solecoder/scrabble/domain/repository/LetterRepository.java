package ch.solecoder.scrabble.domain.repository;

import ch.solecoder.scrabble.domain.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
}
