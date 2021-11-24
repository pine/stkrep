package moe.pine.stkrep.ua;

import moe.pine.stkrep.ua.json.Parser;

import java.io.InputStream;

@FunctionalInterface
public interface CommonUserAgents {
    static CommonUserAgents ofStream(InputStream stream) {
        return new DefaultCommonUserAgents(Parser.parse(stream));
    }

    String sample();
}
