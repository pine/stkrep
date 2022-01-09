package moe.pine.stkrep.ua;

import moe.pine.stkrep.ua.json.UserAgent;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({"unchecked", "ConstantConditions"})
class DefaultCommonUserAgentsTest {
    @Test
    void constructor_userAgents() {
        final List<UserAgent> userAgents =
            List.of(
                new UserAgent("50%", "name1", "system1"),
                new UserAgent("25%", "name2", "system2"),
                new UserAgent("25%", "name3", "system3")
            );
        final DefaultCommonUserAgents commonUserAgents = new DefaultCommonUserAgents(userAgents);
        final EnumeratedDistribution<String> sampler = commonUserAgents.getSampler();

        final List<Pair<String, Double>> actual = sampler.getPmf();
        final List<Pair<String, Double>> expected =
            List.of(
                new Pair<>("name1", 0.5),
                new Pair<>("name2", 0.25),
                new Pair<>("name3", 0.25)
            );
        assertEquals(expected, actual);
    }

    @Test
    void constructor_userAgents_NPE() {
        assertThatThrownBy(() -> new DefaultCommonUserAgents((List<UserAgent>) null))
            .isExactlyInstanceOf(NullPointerException.class)
            .hasMessage("userAgents");
    }

    @Test
    void sample() {
        final EnumeratedDistribution<String> sampler = mock(EnumeratedDistribution.class);

        when(sampler.sample()).thenReturn("sample");

        final CommonUserAgents commonUserAgents = new DefaultCommonUserAgents(sampler);
        assertEquals("sample", commonUserAgents.sample());
    }
}
