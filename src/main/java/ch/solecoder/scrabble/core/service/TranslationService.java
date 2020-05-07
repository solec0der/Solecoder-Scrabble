package ch.solecoder.scrabble.core.service;

import ch.solecoder.scrabble.core.dto.TranslationDTO;
import ch.solecoder.scrabble.core.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.core.service.converter.TranslationConverter;
import ch.solecoder.scrabble.core.service.exception.LanguageNotFoundException;
import ch.solecoder.scrabble.core.service.exception.TranslationKeyNotFoundException;
import ch.solecoder.scrabble.core.service.exception.TranslationNotFoundException;
import ch.solecoder.scrabble.domain.model.Language;
import ch.solecoder.scrabble.domain.model.Translation;
import ch.solecoder.scrabble.domain.model.TranslationKey;
import ch.solecoder.scrabble.domain.repository.LanguageRepository;
import ch.solecoder.scrabble.domain.repository.TranslationKeyRepository;
import ch.solecoder.scrabble.domain.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
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

  public TranslationDTO updateTranslation(long id, TranslationDTO translationDTO) {
    Translation translation = translationRepository.findById(id).orElseThrow(TranslationNotFoundException::new);

    Language language = languageRepository
            .findById(translationDTO.getLanguage().getId())
            .orElseThrow(LanguageNotFoundException::new);

    TranslationKey translationKey = translationKeyRepository
            .findById(translationDTO.getTranslationKeyId())
            .orElseThrow(TranslationKeyNotFoundException::new);

    translation = translation.toBuilder()
            .id(id)
            .language(language)
            .translationKey(translationKey)
            .value(translationDTO.getValue())
            .build();

    return TranslationConverter.convertToDTO(translationRepository.save(translation));
  }

  public TranslationKeyDTO updateTranslationKey(long id, TranslationKeyDTO translationKeyDTO) {
    TranslationKey translationKey = translationKeyRepository.findById(id).orElseThrow(TranslationKeyNotFoundException::new);

    translationKey = translationKey.toBuilder()
            .id(id)
            .key(translationKeyDTO.getKey())
            .build();

    return TranslationConverter.convertToDTO(translationKeyRepository.save(translationKey));
  }

  public void deleteTranslation(long id) {
    translationRepository.deleteById(id);
  }

  public void deleteTranslationKey(long id) {
    translationKeyRepository.deleteById(id);
  }
}
