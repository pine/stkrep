package moe.pine.stkrep.ua.json;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    @Test
    void parse() {
        final String json = """
            [{"percent":"13.4%","useragent":"Mozilla\\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/95.0.4638.69 Safari\\/537.36","system":"Chrome 95.0 Win10"},
             {"percent":"6.8%","useragent":"Mozilla\\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/95.0.4638.54 Safari\\/537.36","system":"Chrome 95.0 Win10"},
             {"percent":"6.7%","useragent":"Mozilla\\/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) Gecko\\/20100101 Firefox\\/94.0","system":"Firefox 94.0 Win10"}]""";
        final InputStream stream =
            new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        final List<UserAgent> userAgents =
            List.of(
                new UserAgent("13.4%", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36", "Chrome 95.0 Win10"),
                new UserAgent("6.8%", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36", "Chrome 95.0 Win10"),
                new UserAgent("6.7%", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) Gecko/20100101 Firefox/94.0", "Firefox 94.0 Win10")
            );
        assertThat(Parser.parse(stream)).isEqualTo(userAgents);
    }
}
