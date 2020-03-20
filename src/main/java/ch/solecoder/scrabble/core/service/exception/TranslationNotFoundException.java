package ch.solecoder.scrabble.core.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such translation")
public class TranslationNotFoundException extends RuntimeException {
}
