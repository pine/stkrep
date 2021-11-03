package moe.pine.stkrep.kabuyoho.extractor;

import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.format.FormattedText;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.Color;

@RequiredArgsConstructor
public class KarteExtractor implements Extractor<FormattedText> {
    private final String title;

    @Override
    public FormattedText extract(Document document) {
        final String cssQuery = String.format(".brnd_karte dl:contains(%s) dd", title);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return new FormattedText(StringUtils.EMPTY, ForegroundColors.BLACK);
        }

        final Color color = TextColorFinder.find(element.classNames()).orElse(ForegroundColors.BLACK);
        return new FormattedText(element.text(), color);
    }
}
