package ch.solecoder.scrabble.core.infrastructure.admin;

import ch.solecoder.scrabble.core.dto.TranslationDTO;
import ch.solecoder.scrabble.core.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.core.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/translations")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TranslationAdminController {

    private final TranslationService translationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TranslationDTO createTranslation(@RequestBody TranslationDTO translationDTO) {
        return translationService.createTranslation(translationDTO);
    }

    @PostMapping("/keys")
    @ResponseStatus(HttpStatus.CREATED)
    public TranslationKeyDTO createTranslationKey(@RequestBody TranslationKeyDTO translationKeyDTO) {
        return translationService.createTranslationKey(translationKeyDTO);
    }

    @PutMapping("/{id}")
    public TranslationDTO updateTranslation(@PathVariable("id") long id, @RequestBody TranslationDTO translationDTO) {
        return translationService.updateTranslation(id, translationDTO);
    }

    @PutMapping("/keys/{id}")
    public TranslationKeyDTO updateTranslationKey(@PathVariable("id") long id, @RequestBody TranslationKeyDTO translationKeyDTO) {
        return translationService.updateTranslationKey(id, translationKeyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTranslation(@PathVariable("id") long id) {
        translationService.deleteTranslation(id);
    }

    @DeleteMapping("/keys/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTranslationKey(@PathVariable("id") long id) {
        translationService.deleteTranslationKey(id);
    }
}
