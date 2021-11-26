package moe.pine.stkrep.ua;

import moe.pine.stkrep.ua.json.Parser;

import java.io.InputStream;
import java.util.Objects;

@FunctionalInterface
public interface CommonUserAgents {
    static CommonUserAgents ofStream(InputStream stream) {
        Objects.requireNonNull(stream, "stream");

        return new DefaultCommonUserAgents(Parser.parse(stream));
    }

    String sample();
}
