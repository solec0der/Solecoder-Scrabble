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
}
