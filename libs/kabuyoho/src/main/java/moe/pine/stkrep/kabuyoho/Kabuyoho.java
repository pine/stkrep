package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.browser.Browser;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import moe.pine.stkrep.kabuyoho.exception.NonRetryableException;
import moe.pine.stkrep.kabuyoho.exception.RetryableException;
import moe.pine.stkrep.kabuyoho.extractor.Extractors;
import moe.pine.stkrep.report.ForecastReport;

@Slf4j
public class Kabuyoho {
    private static final Browser BROWSER = new Browser();
    private static final Extractors EXTRACTORS = new Extractors();

    public ForecastReport find(int code) {
        try {
            final BrowsingResults browsingResults = BROWSER.browse(code);
            final ForecastReport report = EXTRACTORS.extract(browsingResults);

            log.debug("Finished getting the report: {}", report);

            return report;
        } catch (RetryableException e) {
            throw new RetryableException(String.format("Failed to find the report. [code=%d]", code), e);
        } catch (RuntimeException e) {
            throw new NonRetryableException(String.format("Failed to find the report. [code=%d]", code), e);
        }
    }
}
