package ch.solecoder.scrabble.domain.repository;

import ch.solecoder.scrabble.domain.model.TranslationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationKeyRepository extends ScrabbleBaseRepository<TranslationKey, Long> {
}
