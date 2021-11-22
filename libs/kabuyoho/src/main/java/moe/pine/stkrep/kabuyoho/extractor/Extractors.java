package moe.pine.stkrep.kabuyoho.extractor;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.browser.BrowsingResults;
import moe.pine.stkrep.kabuyoho.calculator.DoubleCalculator;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.report.value.Rating;
import moe.pine.stkrep.report.value.RiskOn;
import moe.pine.stkrep.report.value.StockPrice;
import moe.pine.stkrep.report.value.TrendSignal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class Extractors {
    public static final Extractor<String> NAME_EXTRACTOR =
            new SummaryNameExtractor(0);
    public static final Extractor<Integer> PRICE_EXTRACTOR =
            new SummaryBoxIntegerExtractor("株価");
    public static final Extractor<Integer> MARKET_CAPITALIZATION =
            new SummaryBoxIntegerExtractor("時価総額");
    public static final Extractor<TrendSignal> SIGNAL_EXTRACTOR =
            new KarteEnumExtractor<>("今日のシグナル", TrendSignal.class);
    public static final Extractor<RiskOn> LEVEL_EXTRACTOR =
            new KarteEnumExtractor<>("水準", RiskOn.class);
    public static final Extractor<Rating> RATING_EXTRACTOR =
            new KarteEnumExtractor<>("レーティング", TextRange.SECOND_SPAN, Rating.class);
    public static final Extractor<StockPrice> PRICE_BY_ANALYST_EXTRACTOR =
            new KarteEnumExtractor<>("目標株価", StockPrice.class);
    public static final Extractor<StockPrice> PRICE_BY_PBR_EXTRACTOR =
            new KarteEnumExtractor<>("PBR基準", StockPrice.class);
    public static final Extractor<StockPrice> PRICE_BY_PER_EXTRACTOR =
            new KarteEnumExtractor<>("PER基準", StockPrice.class);
    public static final Extractor<Double> PER_YIELD =
            new SummaryBoxDoubleExtractor("PER");
    public static final Extractor<Double> PBR_YIELD =
            new SummaryBoxDoubleExtractor("PBR");
    public static final Extractor<Double> DIVIDEND_YIELD =
            new SummaryBoxDoubleExtractor("配当利回り", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> ROA =
            new SummaryBoxDoubleExtractor("ROA", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> ROE =
            new SummaryBoxDoubleExtractor("ROE", DoubleCalculator.HUNDREDTH_DIVIDER);
    public static final Extractor<Double> EQUITY_RATIO =
            new SummaryBoxDoubleExtractor("自己資本比率", DoubleCalculator.HUNDREDTH_DIVIDER);

    public ForecastReport extract(
            BrowsingResults browsingResults
    ) {
        final Document document = Jsoup.parse(browsingResults.body());

        return new ForecastReport(
                browsingResults.code(),
                browsingResults.uri(),
                NAME_EXTRACTOR.extract(document),
                PRICE_EXTRACTOR.extract(document),
                MARKET_CAPITALIZATION.extract(document),
                SIGNAL_EXTRACTOR.extract(document),
                LEVEL_EXTRACTOR.extract(document),
                RATING_EXTRACTOR.extract(document),
                PRICE_BY_ANALYST_EXTRACTOR.extract(document),
                PRICE_BY_PBR_EXTRACTOR.extract(document),
                PRICE_BY_PER_EXTRACTOR.extract(document),
                PER_YIELD.extract(document),
                PBR_YIELD.extract(document),
                DIVIDEND_YIELD.extract(document),
                ROA.extract(document),
                ROE.extract(document),
                EQUITY_RATIO.extract(document)
        );
    }
}
