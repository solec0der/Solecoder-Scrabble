package ch.solecoder.scrabble.service.converter;

import ch.solecoder.scrabble.domain.model.Translation;
import ch.solecoder.scrabble.domain.model.TranslationKey;
import ch.solecoder.scrabble.dto.TranslationDTO;
import ch.solecoder.scrabble.dto.TranslationKeyDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TranslationConverter {

    public static TranslationKey convertToDomain(TranslationKeyDTO translationKeyDTO) {
        return TranslationKey.builder()
                .id(translationKeyDTO.getId())
                .key(translationKeyDTO.getKey())
                .build();
    }

    public static TranslationKeyDTO convertToDTO(TranslationKey translationKey) {
        return TranslationKeyDTO.builder()
                .id(translationKey.getId())
                .key(translationKey.getKey())
                .build();
    }

    public static TranslationDTO convertToDTO(Translation translation) {
        return TranslationDTO.builder()
                .id(translation.getId())
                .language(LanguageConverter.convertToDTO(translation.getLanguage()))
                .value(translation.getValue())
                .translationKeyId(translation.getTranslationKey().getId())
                .build();
    }
}
