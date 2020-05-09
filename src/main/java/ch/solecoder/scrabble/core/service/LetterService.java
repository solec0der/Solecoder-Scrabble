package ch.solecoder.scrabble.core.service;

import ch.solecoder.scrabble.core.dto.LetterDTO;
import ch.solecoder.scrabble.core.service.converter.LetterConverter;
import ch.solecoder.scrabble.core.service.exception.BadRequestException;
import ch.solecoder.scrabble.domain.model.Letter;
import ch.solecoder.scrabble.domain.repository.LetterRepository;
import liquibase.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public LetterDTO createLetter(LetterDTO letterDTO) {
        if (!letterDTO.isJoker() && StringUtils.isEmpty(letterDTO.getValue())) {
            throw new BadRequestException("If it isn't a joker, value has to be set.");
        } else if (letterDTO.isJoker() && StringUtils.isNotEmpty(letterDTO.getValue())) {
            throw new BadRequestException("Value can't be set, if it's a joker.");
        }

        Letter letter = LetterConverter.convertToDomain(letterDTO);

        return LetterConverter.convertToDTO(letterRepository.save(letter));
    }

    public List<LetterDTO> getAllLetters() {
        return letterRepository.findAll().stream()
                .map(LetterConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
