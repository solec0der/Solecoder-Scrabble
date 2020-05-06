package ch.solecoder.scrabble.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameDTO {

    private long id;
    private String title;
    private LanguageDTO language;
    private List<GameFieldDTO> gameFields;

}
