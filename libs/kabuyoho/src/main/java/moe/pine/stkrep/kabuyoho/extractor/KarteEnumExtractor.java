package moe.pine.stkrep.kabuyoho.extractor;

import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.report.TextEnum;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;

@RequiredArgsConstructor
public class KarteEnumExtractor<E extends Enum<E> & TextEnum> implements Extractor<E> {
    private final String title;
    private final Class<E> clazz;

    @Override
    public E extract(Document document) {
        final String cssQuery = String.format(".brnd_karte dl:contains(%s) dd", title);
        final Element element = document.selectFirst(cssQuery);
        if (element == null) {
            return TextEnum.getEnum(clazz, StringUtils.EMPTY);
        }

        return TextEnum.getEnum(clazz, element.text());
    }
}
