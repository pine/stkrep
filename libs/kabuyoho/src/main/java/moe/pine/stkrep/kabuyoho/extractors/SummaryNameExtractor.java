package moe.pine.stkrep.kabuyoho.extractors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RequiredArgsConstructor
public class SummaryNameExtractor implements Extractor<String> {
    private final int index;

    @Override
    public String extract(Document document) {
        final String cssQuery = String.format(".smary_name li:nth-of-type(%d)", index + 1);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.defaultString(element.text());
    }
}
