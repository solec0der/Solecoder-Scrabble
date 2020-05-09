package ch.solecoder.scrabble.core.service.converter;

import ch.solecoder.scrabble.core.dto.TranslationDTO;
import ch.solecoder.scrabble.core.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.domain.model.Translation;
import ch.solecoder.scrabble.domain.model.TranslationKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TranslationConverter {

    public static TranslationKey convertToDomain(TranslationKeyDTO translationKeyDTO) {
        return translationKeyDTO == null ? null :
                TranslationKey.builder()
                .id(translationKeyDTO.getId())
                .key(translationKeyDTO.getKey())
                .build();
    }

    public static TranslationKeyDTO convertToDTO(TranslationKey translationKey) {
        return translationKey == null ? null :
                TranslationKeyDTO.builder()
                .id(translationKey.getId())
                .key(translationKey.getKey())
                .build();
    }

    public static TranslationDTO convertToDTO(Translation translation) {
        return translation == null ? null :
                TranslationDTO.builder()
                .id(translation.getId())
                .language(LanguageConverter.convertToDTO(translation.getLanguage()))
                .value(translation.getValue())
                .translationKeyId(translation.getTranslationKey().getId())
                .build();
    }
}
