package ch.solecoder.scrabble.core.infrastructure;

import ch.solecoder.scrabble.core.dto.TranslationDTO;
import ch.solecoder.scrabble.core.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.core.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/translations")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TranslationController {

    private final TranslationService translationService;

    @GetMapping("/keys")
    public List<TranslationKeyDTO> getTranslationKeys() {
        return translationService.getTranslationKeys();
    }

    @GetMapping("/keys/{id}")
    public TranslationKeyDTO getTranslationKeyById(@PathVariable("id") long id) {
        return translationService.getTranslationKeyById(id);
    }

    @GetMapping("/as-map")
    public Map<String, List<TranslationDTO>> getTranslationsAsMap() {
        return translationService.getTranslationsAsMap();
    }
}
