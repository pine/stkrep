package moe.pine.stkrep.kabuyoho.internal;

import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.FormattedText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
public class KarteExtractor implements Extractor<FormattedText> {
    private final String title;

    @Override
    @Nullable
    public FormattedText extract(Document document) {
        final Element element = document.selectFirst(".cont_figu_div:contains(" + title + ") .str_b");
        if (element == null) {
            return null;
        }

        return new FormattedText(element.text(), StandardColors.find(element.classNames()));
    }
}
