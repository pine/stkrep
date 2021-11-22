package moe.pine.stkrep.ua;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.ua.browser.Browser;
import moe.pine.stkrep.ua.browser.BrowsingResults;

import java.util.List;

/**
 * User agent collector
 *
 * @see <a href="https://techblog.willshouse.com/2012/01/03/most-common-user-agents/">Most Common User Agents - Tech Blog (wh)</a>
 * @see <a href="https://github.com/Kikobeats/top-user-agents">Kikobeats/top-user-agents: A list of most common User Agent used on Internet.</a>
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MostCommonUserAgents {
    private final Browser browser;

    public MostCommonUserAgents() {
        this(Browser.of());
    }

    public List<UserAgent> collect() {
        final BrowsingResults browsingResults = browser.browse();

        throw new RuntimeException("not implemented");
    }
}
