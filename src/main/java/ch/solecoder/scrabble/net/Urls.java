package ch.solecoder.scrabble.net;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Urls {

    private final Environment environment;

    public String getUrl(String key) {
        return environment.getProperty(key);
    }
}
