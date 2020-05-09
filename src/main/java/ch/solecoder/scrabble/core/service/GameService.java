package ch.solecoder.scrabble.core.service;

import ch.solecoder.scrabble.core.dto.GameDTO;
import ch.solecoder.scrabble.core.service.converter.GameConverter;
import ch.solecoder.scrabble.core.service.exception.LanguageNotFoundException;
import ch.solecoder.scrabble.domain.model.Game;
import ch.solecoder.scrabble.domain.model.Language;
import ch.solecoder.scrabble.domain.repository.GameRepository;
import ch.solecoder.scrabble.domain.repository.LanguageRepository;
import ch.solecoder.scrabble.domain.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final LetterRepository letterRepository;
    private final LanguageRepository languageRepository;

    public GameDTO createGame(GameDTO gameDTO) {
        Language language = languageRepository
                .findById(gameDTO.getLanguage().getId())
                .orElseThrow(LanguageNotFoundException::new);

        Game game = Game.builder()
                .title(gameDTO.getTitle())
                .status(Game.Status.NOT_STARTED)
                .language(language)
                .gameFields(Collections.emptyList())
                .build();

        return GameConverter.convertToDTO(gameRepository.save(game));
    }
}
