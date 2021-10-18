package moe.pine.stkrep.kabuyoho.internal;

import org.jsoup.nodes.Document;


@FunctionalInterface
public interface Extractor<T> {
    T extract(Document document);
}
