package moe.pine.stkrep.kabuyoho.extractors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RequiredArgsConstructor
public class SummaryBoxExtractor implements Extractor<String> {
    private final String title;

    @Override
    public String extract(Document document) {
        final String cssQuery = String.format(".smary_box dl:contains(%s) dd", title);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.defaultString(element.text());
    }
}
