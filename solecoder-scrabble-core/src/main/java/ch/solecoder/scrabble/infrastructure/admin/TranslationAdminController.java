package ch.solecoder.scrabble.infrastructure.admin;

import ch.solecoder.scrabble.dto.TranslationDTO;
import ch.solecoder.scrabble.dto.TranslationKeyDTO;
import ch.solecoder.scrabble.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/translations")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TranslationAdminController {

    private final TranslationService translationService;

    @PostMapping
    public TranslationDTO createTranslation(@RequestBody TranslationDTO translationDTO) {
        return translationService.createTranslation(translationDTO);
    }

    @PostMapping("/keys")
    public TranslationKeyDTO createTranslationKey(@RequestBody TranslationKeyDTO translationKeyDTO) {
        return translationService.createTranslationKey(translationKeyDTO);
    }
}
