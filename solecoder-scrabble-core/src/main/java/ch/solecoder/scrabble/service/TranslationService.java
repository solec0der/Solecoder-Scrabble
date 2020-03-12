package ch.solecoder.scrabble.service;

import ch.solecoder.scrabble.domain.model.Language;
import ch.solecoder.scrabble.domain.model.Translation;
import ch.solecoder.scrabble.domain.model.TranslationKey;
import ch.solecoder.scrabble.domain.repository.LanguageRepository;
import ch.solecoder.scrabble.domain.repository.TranslationKeyRepository;
import ch.solecoder.scrabble.domain.repository.TranslationRepository;
import ch.solecoder.scrabble.dto.TranslationDTO;
import ch.solecoder.scrabble.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.service.converter.TranslationConverter;
import ch.solecoder.scrabble.service.exception.LanguageNotFoundException;
import ch.solecoder.scrabble.service.exception.TranslationKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final TranslationKeyRepository translationKeyRepository;
    private final LanguageRepository languageRepository;

    public TranslationDTO createTranslation(TranslationDTO translationDTO) {
        Language language = languageRepository
                .findById(translationDTO.getLanguage().getId())
                .orElseThrow(LanguageNotFoundException::new);

        TranslationKey translationKey = translationKeyRepository
                .findById(translationDTO.getTranslationKeyId())
                .orElseThrow(TranslationKeyNotFoundException::new);

        Translation translation = Translation.builder()
                .language(language)
                .translationKey(translationKey)
                .value(translationDTO.getValue())
                .build();

        return TranslationConverter.convertToDTO(translationRepository.save(translation));
    }

    public TranslationKeyDTO createTranslationKey(TranslationKeyDTO translationKeyDTO) {
        TranslationKey translationKey = TranslationConverter.convertToDomain(translationKeyDTO);
        return TranslationConverter.convertToDTO(translationKeyRepository.save(translationKey));
    }

    public List<TranslationKeyDTO> getTranslationKeys() {
        List<TranslationKey> translationKeys = translationKeyRepository.findAll();

        return translationKeys.stream()
                .map(TranslationConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public TranslationKeyDTO getTranslationKeyById(long id) {
        return TranslationConverter.convertToDTO(
                translationKeyRepository
                        .findById(id)
                        .orElseThrow(TranslationKeyNotFoundException::new));
    }

    public Map<String, List<TranslationDTO>> getTranslationsAsMap() {
        Map<String, List<TranslationDTO>> translationsAsMap = new HashMap<>();
        List<TranslationKey> translationKeys = translationKeyRepository.findAll();

        for (TranslationKey translationKey : translationKeys) {
            List<TranslationDTO> translations = translationKey
                    .getTranslations()
                    .stream()
                    .map(TranslationConverter::convertToDTO)
                    .collect(Collectors.toList());

            translationsAsMap.put(translationKey.getKey(), translations);
        }
        return translationsAsMap;
    }
}
