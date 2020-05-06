package ch.solecoder.scrabble.core.service.converter;

import ch.solecoder.scrabble.core.dto.GameDTO;
import ch.solecoder.scrabble.domain.model.Game;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameConverter {

    public static Game convertToDomain(GameDTO gameDTO) {
        return gameDTO == null ? null :
                Game.builder()
                .id(gameDTO.getId())
                .title(gameDTO.getTitle())
                .language(LanguageConverter.convertToDomain(gameDTO.getLanguage()))
                .gameFields(
                        gameDTO.getGameFields().stream()
                                .map(GameFieldConverter::convertToDomain)
                                .collect(Collectors.toList())
                ).build();
    }

    public static GameDTO convertToDTO(Game game) {
        return game == null ? null :
                GameDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .language(LanguageConverter.convertToDTO(game.getLanguage()))
                .gameFields(
                        game.getGameFields().stream()
                                .map(GameFieldConverter::convertToDTO)
                                .collect(Collectors.toList())
                ).build();
    }
}
