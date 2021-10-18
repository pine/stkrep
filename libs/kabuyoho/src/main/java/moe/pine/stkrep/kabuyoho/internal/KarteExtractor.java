package moe.pine.stkrep.kabuyoho.internal;

import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.format.FormattedText;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RequiredArgsConstructor
public class KarteExtractor implements Extractor<FormattedText> {
    private final String title;

    @Override
    public FormattedText extract(Document document) {
        final Element element = document.selectFirst(".cont_figu_div:contains(" + title + ") .str_b");
        if (element == null) {
            return new FormattedText(StringUtils.EMPTY, ForegroundColors.BLACK);
        }

        return new FormattedText(element.text(), TextColorFinder.find(element.classNames()));
    }
}
