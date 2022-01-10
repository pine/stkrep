package moe.pine.stkrep.ua;

import moe.pine.stkrep.ua.json.Parser;
import moe.pine.stkrep.ua.json.UserAgent;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class CommonUserAgentsTest {
    @Test
    void ofStream() {
        final InputStream stream = mock(InputStream.class);
        final List<UserAgent> userAgents =
            List.of(new UserAgent("100%", "name1", "system1"));

        try (var mocked = mockStatic(Parser.class)) {
            mocked.when(() -> Parser.parse(any())).thenReturn(userAgents);

            final DefaultCommonUserAgents commonUserAgents =
                (DefaultCommonUserAgents) CommonUserAgents.ofStream(stream);
            final EnumeratedDistribution<String> sampler = commonUserAgents.getSampler();

            final List<Pair<String, Double>> actual = sampler.getPmf();
            final List<Pair<String, Double>> expected = List.of(new Pair<>("name1", 1.0));
            assertEquals(expected, actual);

            mocked.verify(() -> Parser.parse(stream));
        }
    }
}
