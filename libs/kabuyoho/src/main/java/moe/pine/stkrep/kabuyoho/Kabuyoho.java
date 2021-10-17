package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.internal.Browser;
import moe.pine.stkrep.kabuyoho.internal.BrowsingResults;
import moe.pine.stkrep.kabuyoho.internal.Extractors;
import moe.pine.stkrep.kabuyoho.models.Report;

@Slf4j
public class Kabuyoho {
    private final Browser browser = new Browser();
    private final Extractors extractors = new Extractors();

    public Report find(String code) {
        final BrowsingResults browsingResults = browser.browse(code);
        return extractors.extract(browsingResults);
    }
}
