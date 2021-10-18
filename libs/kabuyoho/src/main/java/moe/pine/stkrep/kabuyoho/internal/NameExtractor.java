package moe.pine.stkrep.kabuyoho.internal;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NameExtractor implements Extractor<String> {
    @Override
    public String extract(Document document) {
        final Element element = document.selectFirst("#report .stock_name");
        if (element == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.defaultString(element.text());
    }
}
