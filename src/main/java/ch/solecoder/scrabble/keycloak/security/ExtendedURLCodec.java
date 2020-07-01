package ch.solecoder.scrabble.keycloak.security;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.util.StringUtils.isEmpty;

public class ExtendedURLCodec extends URLCodec {

    public static final ExtendedURLCodec INSTANCE = new ExtendedURLCodec(CharEncoding.UTF_8);

    private ExtendedURLCodec(String encoding) {
        super(encoding);
    }

    @Override
    public String encode(String str) {
        try {
            return super.encode(str);
        } catch (EncoderException e) {
            // shouldn't ever happen, UTF-8 should be supported everywhere
            throw new IllegalStateException(e);
        }
    }

    public char[] encodeChars(char[] chars) {
        if (isEmpty(chars)) {
            return chars;
        }

        ByteBuffer byteBuffer = UTF_8.encode(CharBuffer.wrap(chars));
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        byte[] encodedBytes = encode(bytes);
        CharBuffer charBuffer = UTF_8.decode(ByteBuffer.wrap(encodedBytes));
        char[] encodedChars = Arrays.copyOfRange(charBuffer.array(), charBuffer.position(), charBuffer.limit());

        Arrays.fill(byteBuffer.array(), Byte.MIN_VALUE);
        Arrays.fill(encodedBytes, Byte.MIN_VALUE);
        Arrays.fill(charBuffer.array(), Character.MIN_VALUE);

        return encodedChars;
    }
}
