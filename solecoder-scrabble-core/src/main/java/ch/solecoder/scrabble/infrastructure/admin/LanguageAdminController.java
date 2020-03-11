package ch.solecoder.scrabble.infrastructure.admin;

import ch.solecoder.scrabble.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/languages")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class LanguageAdminController {

    private final LanguageService languageService;

}
