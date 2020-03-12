package ch.solecoder.scrabble.service;

import ch.solecoder.scrabble.domain.model.Language;
import ch.solecoder.scrabble.domain.repository.LanguageRepository;
import ch.solecoder.scrabble.dto.LanguageDTO;
import ch.solecoder.scrabble.service.converter.LanguageConverter;
import ch.solecoder.scrabble.service.exception.LanguageNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        Language language = LanguageConverter.convertToDomain(languageDTO);
        return LanguageConverter.convertToDTO(languageRepository.save(language));
    }

    public List<LanguageDTO> getLanguages() {
        List<Language> languages = languageRepository.findAll();

        return languages.stream()
                .map(LanguageConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public LanguageDTO getLanguageById(long id) {
        Language language = languageRepository.findById(id).orElseThrow(LanguageNotFoundException::new);
        return LanguageConverter.convertToDTO(language);
    }

    public LanguageDTO updateLanguage(long languageId, LanguageDTO languageDTO) {
        Language language = languageRepository.findById(languageId).orElseThrow(LanguageNotFoundException::new);

        language = language.toBuilder()
                .displayValue(languageDTO.getDisplayValue())
                .langCode(languageDTO.getLangCode())
                .uiLangCode(languageDTO.getUiLangCode())
                .build();

        return LanguageConverter.convertToDTO(languageRepository.save(language));
    }

    public void deleteLanguage(long languageId) {
        languageRepository.deleteById(languageId);
    }
}
