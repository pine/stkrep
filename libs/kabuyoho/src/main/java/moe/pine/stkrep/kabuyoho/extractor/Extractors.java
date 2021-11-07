package moe.pine.stkrep.kabuyoho.extractor;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import moe.pine.stkrep.kabuyoho.calculator.DoubleCalculator;
import moe.pine.stkrep.report.text.TrendSignal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class Extractors {
    public static final Extractor<String> NAME_EXTRACTOR = new SummaryNameExtractor(0);
    public static final Extractor<Integer> PRICE_EXTRACTOR = new SummaryBoxIntegerExtractor("株価");
    public static final Extractor<Integer> MARKET_CAPITALIZATION = new SummaryBoxIntegerExtractor("時価総額");
    public static final Extractor<TrendSignal> SIGNAL_EXTRACTOR =
            new KarteEnumExtractor<>("今日のシグナル", TrendSignal.class);
    public static final Extractor<FormattedText> LEVEL_EXTRACTOR = new KarteStringExtractor("水準");
    public static final Extractor<FormattedText> RATING_EXTRACTOR = new KarteStringExtractor("レーティング");
    public static final Extractor<FormattedText> FORECAST_BY_ANALYST_EXTRACTOR = new KarteStringExtractor("目標株価");
    public static final Extractor<FormattedText> FORECAST_BY_PBR_EXTRACTOR = new KarteStringExtractor("PBR基準");
    public static final Extractor<FormattedText> FORECAST_BY_PER_EXTRACTOR = new KarteStringExtractor("PER基準");
    public static final Extractor<Double> PER_YIELD = new SummaryBoxDoubleExtractor("PER");
    public static final Extractor<Double> PBR_YIELD = new SummaryBoxDoubleExtractor("PBR");
    public static final Extractor<Double> DIVIDEND_YIELD =
            new SummaryBoxDoubleExtractor("配当利回り", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> ROA =
            new SummaryBoxDoubleExtractor("ROA", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> ROE =
            new SummaryBoxDoubleExtractor("ROE", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> EQUITY_RATIO =
            new SummaryBoxDoubleExtractor("自己資本比率", DoubleCalculator.HUNDREDTH_DIVIDER);

    public Forecast extract(
            BrowsingResults browsingResults
    ) {
        final Document document = Jsoup.parse(browsingResults.body());

        return new Forecast(
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
                ROA.extract(document),
                ROE.extract(document),
                EQUITY_RATIO.extract(document)
        );
    }
}
