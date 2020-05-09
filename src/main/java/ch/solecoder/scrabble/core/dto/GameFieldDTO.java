package ch.solecoder.scrabble.core.dto;

import ch.solecoder.scrabble.domain.model.FieldType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GameFieldDTO {

    private long id;
    private int xPosition;
    private int yPosition;
    private long gameId;
    private LetterDTO letter;
    private FieldType fieldType;

}
