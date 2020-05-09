package ch.solecoder.scrabble.core.service.converter;

import ch.solecoder.scrabble.core.dto.LetterDTO;
import ch.solecoder.scrabble.domain.model.Letter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LetterConverter {

    public static Letter convertToDomain(LetterDTO letterDTO) {
        return letterDTO == null ? null :
                Letter.builder()
                .id(letterDTO.getId())
                .value(letterDTO.getValue())
                .score(letterDTO.getScore())
                .joker(letterDTO.isJoker())
                .build();
    }

    public static LetterDTO convertToDTO(Letter letter) {
        return letter == null ? null :
                LetterDTO.builder()
                .id(letter.getId())
                .value(letter.getValue())
                .score(letter.getScore())
                .joker(letter.isJoker())
                .build();
    }
}
