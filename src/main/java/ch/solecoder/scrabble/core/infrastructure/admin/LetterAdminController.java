package ch.solecoder.scrabble.core.infrastructure.admin;

import ch.solecoder.scrabble.core.dto.LetterDTO;
import ch.solecoder.scrabble.core.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/letters")
public class LetterAdminController {

    private final LetterService letterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LetterDTO createLetter(@RequestBody LetterDTO letterDTO) {
        return letterService.createLetter(letterDTO);
    }

    @GetMapping
    public List<LetterDTO> getAllLetters() {
        return letterService.getAllLetters();
    }
}
