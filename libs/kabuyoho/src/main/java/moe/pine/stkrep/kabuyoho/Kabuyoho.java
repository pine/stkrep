package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.browser.Browser;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import moe.pine.stkrep.kabuyoho.extractor.Extractors;
import moe.pine.stkrep.report.Forecast;

@Slf4j
public class Kabuyoho {
    private static final Browser BROWSER = new Browser();
    private static final Extractors EXTRACTORS = new Extractors();

    public Forecast find(int code) {
        final BrowsingResults browsingResults = BROWSER.browse(code);
        final Forecast forecast = EXTRACTORS.extract(browsingResults);

        log.debug("Finished getting the report: {}", forecast);

        return forecast;
    }
}
