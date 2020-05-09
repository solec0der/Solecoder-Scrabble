package ch.solecoder.scrabble.core.dto;

import ch.solecoder.scrabble.domain.model.Game;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GameDTO {

    private long id;
    private String title;
    private Game.Status status;
    private LanguageDTO language;
    private List<GameFieldDTO> gameFields;

}
