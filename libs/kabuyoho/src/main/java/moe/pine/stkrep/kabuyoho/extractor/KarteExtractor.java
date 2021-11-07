package moe.pine.stkrep.kabuyoho.extractor;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RequiredArgsConstructor
public abstract class KarteExtractor<T> implements Extractor<T> {
    private final String title;

    @Override
    public T extract(Document document) {
        final String cssQuery = String.format(".brnd_karte dl:contains(%s) dd", title);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return onExtract(StringUtils.EMPTY);
        }

        return onExtract(element.text());
    }

    protected abstract T onExtract(String text);
}
