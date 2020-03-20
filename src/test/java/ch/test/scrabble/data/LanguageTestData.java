package ch.test.scrabble.data;

import ch.solecoder.scrabble.core.dto.LanguageDTO;
import ch.solecoder.scrabble.core.service.converter.LanguageConverter;
import ch.solecoder.scrabble.domain.model.Language;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageTestData {

    public static List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();

        Language language = Language.builder()
                .id(1L)
                .displayValue("Deutsch")
                .langCode("de-CH")
                .uiLangCode("de_CH")
                .build();

        languages.add(language);

        language = Language.builder()
                .id(2L)
                .displayValue("English")
                .langCode("en-US")
                .uiLangCode("en_US")
                .build();

        languages.add(language);

        return languages;
    }

    public static List<LanguageDTO> getLanguageDTOS() {
        return getLanguages().stream()
                .map(LanguageConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
