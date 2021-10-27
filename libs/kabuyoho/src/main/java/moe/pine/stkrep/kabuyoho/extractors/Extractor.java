package moe.pine.stkrep.kabuyoho.extractors;

import org.jsoup.nodes.Document;


@FunctionalInterface
public interface Extractor<T> {
    T extract(Document document);
}
