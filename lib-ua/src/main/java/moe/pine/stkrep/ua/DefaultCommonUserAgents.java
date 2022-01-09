package moe.pine.stkrep.ua;

import lombok.AccessLevel;
import lombok.Getter;
import moe.pine.stkrep.ua.json.UserAgent;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.List;
import java.util.Objects;

/**
 * User agent collector
 *
 * @see <a href="https://techblog.willshouse.com/2012/01/03/most-common-user-agents/">Most Common User Agents - Tech Blog (wh)</a>
 * @see <a href="https://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/distribution/EnumeratedDistribution.html">EnumeratedDistribution (Apache Commons Math 3.6.1 API)</a>
 */
@Getter(AccessLevel.PROTECTED)
public class DefaultCommonUserAgents implements CommonUserAgents {
    private final EnumeratedDistribution<String> sampler;

    public DefaultCommonUserAgents(List<UserAgent> userAgents) {
        Objects.requireNonNull(userAgents, "userAgents");

        final List<Pair<String, Double>> pmf =
            userAgents.stream()
                .map(userAgent -> {
                    final String parsable = userAgent.percent().replace("%", "");
                    final double weight = Double.parseDouble(parsable) / 100.0;
                    return Pair.create(userAgent.name(), weight);
                })
                .toList();

        sampler = new EnumeratedDistribution<>(pmf);
    }

    protected DefaultCommonUserAgents(EnumeratedDistribution<String> sampler) {
        this.sampler = Objects.requireNonNull(sampler, "sampler");
    }

    @Override
    public String sample() {
        return sampler.sample();
    }
}
