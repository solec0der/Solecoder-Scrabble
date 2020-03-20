package ch.solecoder.scrabble.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TranslationKeyDTO {

    private long id;
    private String key;

}
