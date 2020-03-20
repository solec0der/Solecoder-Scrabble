package ch.solecoder.scrabble.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TranslationDTO {

    private long id;
    private long translationKeyId;
    private LanguageDTO language;
    private String value;

}
