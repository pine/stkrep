package moe.pine.stkrep.kabuyoho.internal;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PriceExtractor implements Extractor<Integer> {
    private static final Pattern PRICE_PATTERN = Pattern.compile("\\p{Digit}+");

    @Override
    @Nullable
    public Integer extract(Document document) {
        final Element reportElement = document.getElementById("report");
        if (reportElement == null) {
            // TODO
            return null;
        }

        final Elements priceElements = reportElement.getElementsByClass("price");
        if (priceElements.isEmpty()) {
            // TODO
            return null;
        }

        final String priceText = priceElements.get(0).text().replace(",", "");
        final Matcher matcher = PRICE_PATTERN.matcher(priceText);
        if (!matcher.find()) {
            // TODO
            return null;
        }

        try {
            return Integer.parseInt(matcher.group());
        } catch (NumberFormatException e) {
            log.error("Unable to parse price text. [price-text={}]", priceText, e);
            return null;
        }
    }
}
