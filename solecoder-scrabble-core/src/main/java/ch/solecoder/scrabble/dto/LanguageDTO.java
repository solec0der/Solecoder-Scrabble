package ch.solecoder.scrabble.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LanguageDTO {

    private long id;
    private String language;
    private String langCode;
    private String uiLangCode;

}
