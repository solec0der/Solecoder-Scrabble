package ch.solecoder.scrabble.domain.repository;

import ch.solecoder.scrabble.domain.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ScrabbleBaseRepository<Game, Long> {
}
