package moe.pine.stkrep.kabuyoho.internal;

import org.jsoup.nodes.Document;
import org.springframework.lang.Nullable;


@FunctionalInterface
public interface Extractor<T> {
    @Nullable
    T extract(Document document);
}
