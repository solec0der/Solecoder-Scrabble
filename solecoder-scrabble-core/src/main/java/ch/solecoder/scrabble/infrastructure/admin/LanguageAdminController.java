package ch.solecoder.scrabble.infrastructure.admin;

import ch.solecoder.scrabble.dto.LanguageDTO;
import ch.solecoder.scrabble.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/languages")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
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
