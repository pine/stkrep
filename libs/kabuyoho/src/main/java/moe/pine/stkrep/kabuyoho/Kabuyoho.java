package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.internal.Browser;
import moe.pine.stkrep.kabuyoho.internal.BrowsingResults;
import moe.pine.stkrep.kabuyoho.internal.Extractors;

@Slf4j
public class Kabuyoho {
    private static final Browser BROWSER = new Browser();
    private static final Extractors EXTRACTORS = new Extractors();

    public Report find(int code) {
        final BrowsingResults browsingResults = BROWSER.browse(code);
        return EXTRACTORS.extract(browsingResults);
    }
}
