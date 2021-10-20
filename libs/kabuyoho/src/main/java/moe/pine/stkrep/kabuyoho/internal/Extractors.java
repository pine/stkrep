package moe.pine.stkrep.kabuyoho.internal;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.kabuyoho.Report;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class Extractors {
    public static final Extractor<Integer> PRICE_EXTRACTOR = new PriceExtractor();
    public static final Extractor<String> NAME_EXTRACTOR = new NameExtractor();
    public static final Extractor<FormattedText> SIGNAL_EXTRACTOR = new KarteExtractor("今日のシグナル");
    public static final Extractor<FormattedText> LEVEL_EXTRACTOR = new KarteExtractor("水準");
    public static final Extractor<FormattedText> RATING_EXTRACTOR = new KarteExtractor("レーティング");
    public static final Extractor<FormattedText> FORECAST_BY_ANALYST_EXTRACTOR = new KarteExtractor("目標株価");
    public static final Extractor<FormattedText> FORECAST_BY_PBR_EXTRACTOR = new KarteExtractor("PBR基準");
    public static final Extractor<FormattedText> FORECAST_BY_PER_EXTRACTOR = new KarteExtractor("PER基準");

    public Report extract(
            BrowsingResults browsingResults
    ) {
        final Document document = Jsoup.parse(browsingResults.body());

        return new Report(
                browsingResults.code(),
                browsingResults.uri(),
                NAME_EXTRACTOR.extract(document),
                PRICE_EXTRACTOR.extract(document),
                SIGNAL_EXTRACTOR.extract(document),
                LEVEL_EXTRACTOR.extract(document),
                RATING_EXTRACTOR.extract(document),
                FORECAST_BY_ANALYST_EXTRACTOR.extract(document),
                FORECAST_BY_PBR_EXTRACTOR.extract(document),
                FORECAST_BY_PER_EXTRACTOR.extract(document)
        );
    }
}
