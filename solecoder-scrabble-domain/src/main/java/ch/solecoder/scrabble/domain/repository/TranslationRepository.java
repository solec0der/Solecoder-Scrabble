package ch.solecoder.scrabble.domain.repository;

import ch.solecoder.scrabble.domain.model.Translation;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends ScrabbleBaseRepository<Translation, Long> {
}
