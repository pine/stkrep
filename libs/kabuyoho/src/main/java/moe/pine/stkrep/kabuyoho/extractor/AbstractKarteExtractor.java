package moe.pine.stkrep.kabuyoho.extractor;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RequiredArgsConstructor
public abstract class AbstractKarteExtractor<T> implements Extractor<T> {
    private final String title;
    private final TextRange textRange;

    public AbstractKarteExtractor(String title) {
        this(title, TextRange.ALL);
    }

    @Override
    public T extract(Document document) {
        final String cssQuery = String.format(".brnd_karte dl:contains(%s) dd", title);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return onExtract(StringUtils.EMPTY);
        }

        final Elements spanElements = element.select("span");
        return switch (textRange) {
            case ALL -> onExtract(element.text());
            case FIRST_SPAN -> onExtract(spanElements.eq(0).text());
            case SECOND_SPAN -> onExtract(spanElements.eq(1).text());
        };
    }

    protected abstract T onExtract(String text);
}
