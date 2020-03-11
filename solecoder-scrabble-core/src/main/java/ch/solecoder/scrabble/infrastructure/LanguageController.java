package ch.solecoder.scrabble.infrastructure;

import ch.solecoder.scrabble.dto.LanguageDTO;
import ch.solecoder.scrabble.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping(value = "/{id}")
    public LanguageDTO getLanguageById(@PathVariable("id") long id) {
        return languageService.getLanguageById(id);
    }
}
