package ch.solecoder.scrabble.core.infrastructure.admin;

import ch.solecoder.scrabble.core.dto.LanguageDTO;
import ch.solecoder.scrabble.core.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/languages")
public class LanguageAdminController {

    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDTO createLanguage(@RequestBody LanguageDTO languageDTO) {
        return languageService.createLanguage(languageDTO);
    }

    @PutMapping(value = "/{id}")
    public LanguageDTO updateLanguage(@PathVariable("id") long id, @RequestBody LanguageDTO languageDTO) {
        return languageService.updateLanguage(id, languageDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguage(@PathVariable("id") long id) {
        this.languageService.deleteLanguage(id);
    }
}
