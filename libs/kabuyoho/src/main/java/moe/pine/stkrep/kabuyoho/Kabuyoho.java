package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.browser.Browser;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import moe.pine.stkrep.kabuyoho.extractors.Extractors;

@Slf4j
public class Kabuyoho {
    private static final Browser BROWSER = new Browser();
    private static final Extractors EXTRACTORS = new Extractors();

    public Report find(int code) {
        final BrowsingResults browsingResults = BROWSER.browse(code);
        final Report report = EXTRACTORS.extract(browsingResults);

        log.debug("Finished getting the report: {}", report);

        return report;
    }
}
