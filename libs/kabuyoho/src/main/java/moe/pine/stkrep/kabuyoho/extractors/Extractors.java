package moe.pine.stkrep.kabuyoho.extractors;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.kabuyoho.Report;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class Extractors {
    public static final Extractor<String> NAME_EXTRACTOR = new SummaryNameExtractor(0);
    public static final Extractor<String> PRICE_EXTRACTOR = new SummaryBoxExtractor("株価");
    public static final Extractor<String> MARKET_CAPITALIZATION = new SummaryBoxExtractor("時価総額");
    public static final Extractor<FormattedText> SIGNAL_EXTRACTOR = new KarteExtractor("今日のシグナル");
    public static final Extractor<FormattedText> LEVEL_EXTRACTOR = new KarteExtractor("水準");
    public static final Extractor<FormattedText> RATING_EXTRACTOR = new KarteExtractor("レーティング");
    public static final Extractor<FormattedText> FORECAST_BY_ANALYST_EXTRACTOR = new KarteExtractor("目標株価");
    public static final Extractor<FormattedText> FORECAST_BY_PBR_EXTRACTOR = new KarteExtractor("PBR基準");
    public static final Extractor<FormattedText> FORECAST_BY_PER_EXTRACTOR = new KarteExtractor("PER基準");
    public static final Extractor<String> PER_YIELD = new SummaryBoxExtractor("PER");
    public static final Extractor<String> PBR_YIELD = new SummaryBoxExtractor("PBR");
    public static final Extractor<String> DIVIDEND_YIELD = new SummaryBoxExtractor("配当利回り");
    public static final Extractor<String> EQUITY_RATIO = new SummaryBoxExtractor("自己資本比率");

    public Report extract(
            BrowsingResults browsingResults
    ) {
        final Document document = Jsoup.parse(browsingResults.body());

        return new Report(
                browsingResults.code(),
                browsingResults.uri(),
                NAME_EXTRACTOR.extract(document),
                PRICE_EXTRACTOR.extract(document),
                MARKET_CAPITALIZATION.extract(document),
                SIGNAL_EXTRACTOR.extract(document),
                LEVEL_EXTRACTOR.extract(document),
                RATING_EXTRACTOR.extract(document),
                FORECAST_BY_ANALYST_EXTRACTOR.extract(document),
                FORECAST_BY_PBR_EXTRACTOR.extract(document),
                FORECAST_BY_PER_EXTRACTOR.extract(document),
                PER_YIELD.extract(document),
                PBR_YIELD.extract(document),
                DIVIDEND_YIELD.extract(document),
                EQUITY_RATIO.extract(document)
        );
    }
}
