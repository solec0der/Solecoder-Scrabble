package ch.solecoder.scrabble.core.dto;

import ch.solecoder.scrabble.domain.model.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameFieldDTO {

    private long id;
    private int xPosition;
    private int yPosition;
    private long gameId;
    private LetterDTO letter;
    private FieldType fieldType;

}
