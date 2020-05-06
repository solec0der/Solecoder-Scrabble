package ch.solecoder.scrabble.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class LetterDTO {

    private long id;
    private String value;
    private int score;
    private boolean joker;

}
