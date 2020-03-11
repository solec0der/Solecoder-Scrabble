package ch.solecoder.scrabble.service.converter;

import ch.solecoder.scrabble.domain.model.Language;
import ch.solecoder.scrabble.dto.LanguageDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageConverter {

    public static Language convertToDomain(LanguageDTO languageDTO) {
        return languageDTO == null ? null :
                Language.builder()
                        .id(languageDTO.getId())
                        .language(languageDTO.getLanguage())
                        .langCode(languageDTO.getLangCode())
                        .uiLangCode(languageDTO.getUiLangCode())
                        .build();
    }

    public static LanguageDTO convertToDTO(Language language) {
        return language == null ? null :
                LanguageDTO.builder()
                        .id(language.getId())
                        .language(language.getLanguage())
                        .langCode(language.getLangCode())
                        .uiLangCode(language.getUiLangCode())
                        .build();
    }
}
