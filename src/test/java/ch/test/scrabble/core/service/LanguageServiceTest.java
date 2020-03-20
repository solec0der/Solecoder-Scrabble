package ch.test.scrabble.core.service;

import ch.solecoder.scrabble.ScrabbleRestApplication;
import ch.solecoder.scrabble.core.dto.LanguageDTO;
import ch.solecoder.scrabble.core.service.LanguageService;
import ch.solecoder.scrabble.core.service.exception.LanguageNotFoundException;
import ch.solecoder.scrabble.domain.repository.LanguageRepository;
import ch.test.scrabble.data.LanguageTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= ScrabbleRestApplication.class)
public class LanguageServiceTest {

    @InjectMocks
    private LanguageService languageService;

    @Mock
    private LanguageRepository languageRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldReturnLanguages() throws JsonProcessingException, JSONException {
        when(languageRepository.findAll()).thenReturn(LanguageTestData.getLanguages());

        List<LanguageDTO> result = languageService.getLanguages();
        List<LanguageDTO> excepcted = LanguageTestData.getLanguageDTOS();

        String resultJson = objectMapper.writeValueAsString(result);
        String exceptedJson = objectMapper.writeValueAsString(excepcted);

        JSONAssert.assertEquals(exceptedJson, resultJson, false);
    }

    @Test
    public void shouldReturnLanguageById() throws JsonProcessingException, JSONException {
        when(languageRepository.findById(1L)).thenReturn(Optional.of(LanguageTestData.getLanguages().get(0)));

        LanguageDTO result = languageService.getLanguageById(1L);
        LanguageDTO expeted = LanguageTestData.getLanguageDTOS().get(0);

        String resultJson = objectMapper.writeValueAsString(result);
        String excpectedJson = objectMapper.writeValueAsString(expeted);

        JSONAssert.assertEquals(excpectedJson, resultJson, false);
    }

    @Test(expected = LanguageNotFoundException.class)
    public void shouldReturn404WithInvalidId() {
        when(languageRepository.findById(anyLong())).thenThrow(LanguageNotFoundException.class);

        languageService.getLanguageById(1L);
    }
}
