package ch.solecoder.scrabble.core.infrastructure;

import ch.solecoder.scrabble.core.dto.GameDTO;
import ch.solecoder.scrabble.core.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameService.createGame(gameDTO);
    }
}
