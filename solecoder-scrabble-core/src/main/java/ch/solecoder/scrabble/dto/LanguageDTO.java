package ch.solecoder.scrabble.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class LanguageDTO {

    private long id;
    private String displayValue;
    private String langCode;
    private String uiLangCode;

}
