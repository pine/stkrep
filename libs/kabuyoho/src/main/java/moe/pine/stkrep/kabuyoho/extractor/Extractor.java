package moe.pine.stkrep.kabuyoho.extractor;

import org.jsoup.nodes.Document;


@FunctionalInterface
public interface Extractor<T> {
    T extract(Document document);
}
