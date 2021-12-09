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
    private static final Extractors EXTRACTORS = new Extractors();

    private final Browser browser;

    public Kabuyoho(
            UserAgentSupplier userAgentSupplier
    ) {
        browser = new Browser(userAgentSupplier);
    }

    public ForecastReport find(int code) {
        try {
            final BrowsingResults browsingResults = browser.browse(code);
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
