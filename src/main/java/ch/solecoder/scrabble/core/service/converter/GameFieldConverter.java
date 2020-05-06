package ch.solecoder.scrabble.core.service.converter;

import ch.solecoder.scrabble.core.dto.GameFieldDTO;
import ch.solecoder.scrabble.domain.model.GameField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameFieldConverter {

    public static GameField convertToDomain(GameFieldDTO gameFieldDTO) {
        return GameField.builder()
                .id(gameFieldDTO.getId())
                .xPosition(gameFieldDTO.getXPosition())
                .yPosition(gameFieldDTO.getYPosition())
                .letter(LetterConverter.convertToDomain(gameFieldDTO.getLetter()))
                .fieldType(gameFieldDTO.getFieldType())
                .build();
    }

    public static GameFieldDTO convertToDTO(GameField gameField) {
        return GameFieldDTO.builder()
                .id(gameField.getId())
                .xPosition(gameField.getXPosition())
                .yPosition(gameField.getYPosition())
                .gameId(gameField.getGame().getId())
                .letter(LetterConverter.convertToDTO(gameField.getLetter()))
                .fieldType(gameField.getFieldType())
                .build();
    }
}
