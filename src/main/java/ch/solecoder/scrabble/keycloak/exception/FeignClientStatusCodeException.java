package ch.solecoder.scrabble.keycloak.exception;

import feign.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.util.Optional.ofNullable;

@Slf4j
@Getter
public class FeignClientStatusCodeException extends RuntimeException {
    private static final long serialVersionUID = -4551255538986147003L;

    private static final String DEFAULT_ERROR_MESSAGE = "Error occurred contacting another service.";

    private final int statusCode;
    private final String errorMessage;

    public FeignClientStatusCodeException(int statusCode, Response.Body responseBody) {
        super(ofNullable(tryToExtractErrorMessage(responseBody)).orElse(DEFAULT_ERROR_MESSAGE));
        this.statusCode = statusCode;
        this.errorMessage = getMessage();
    }

    private static String tryToExtractErrorMessage(Response.Body responseBody) {
        if (responseBody == null) {
            return null;
        }
        try {
            byte[] bytes = StreamUtils.copyToByteArray(responseBody.asInputStream());
            JSONObject error = new JSONObject(new String(bytes, StandardCharsets.UTF_8));
            if (error.has("message")) {
                return error.getString("message");
            }
        } catch (IOException | JSONException e) {
            log.warn("Could not extract error message from the client response body", e);
        }
        return null;
    }
}
