package moe.pine.stkrep.kabuyoho.internal;

import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.kabuyoho.models.Color;
import moe.pine.stkrep.kabuyoho.models.ColoredText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
public class KarteExtractor implements Extractor<ColoredText> {
    private final String title;

    @Override
    @Nullable
    public ColoredText extract(Document document) {
        final Element element = document.selectFirst(".cont_figu_div:contains(" + title + ") .str_b");
        if (element == null) {
            return null;
        }

        return new ColoredText(element.text(), Color.of(element.classNames()));
    }
}
