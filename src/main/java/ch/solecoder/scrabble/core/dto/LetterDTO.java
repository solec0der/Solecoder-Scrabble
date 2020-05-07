package ch.solecoder.scrabble.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class LetterDTO {

    private long id;
    private String value;

    @Size(min = 1, message = "Score must be bigger than 0")
    private int score;
    private boolean joker;

}
