package ch.solecoder.scrabble.core.service.converter;

import ch.solecoder.scrabble.core.dto.LanguageDTO;
import ch.solecoder.scrabble.domain.model.Language;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageConverter {

    public static Language convertToDomain(LanguageDTO languageDTO) {
        return languageDTO == null ? null :
                Language.builder()
                        .id(languageDTO.getId())
                        .displayValue(languageDTO.getDisplayValue())
                        .langCode(languageDTO.getLangCode())
                        .uiLangCode(languageDTO.getUiLangCode())
                        .build();
    }

    public static LanguageDTO convertToDTO(Language language) {
        return language == null ? null :
                LanguageDTO.builder()
                        .id(language.getId())
                        .displayValue(language.getDisplayValue())
                        .langCode(language.getLangCode())
                        .uiLangCode(language.getUiLangCode())
                        .build();
    }
}
