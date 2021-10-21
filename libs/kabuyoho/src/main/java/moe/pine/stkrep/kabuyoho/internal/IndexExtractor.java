package moe.pine.stkrep.kabuyoho.internal;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RequiredArgsConstructor
public class IndexExtractor implements Extractor<String> {
    private final String text;

    @Override
    public String extract(Document document) {
        final Element element = document.selectFirst(".content_index tr:contains(" + text + ") .num");
        if (element == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.defaultString(element.text());
    }
}
