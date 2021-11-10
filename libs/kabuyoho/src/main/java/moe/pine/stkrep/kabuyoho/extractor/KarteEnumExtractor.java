package moe.pine.stkrep.kabuyoho.extractor;

import moe.pine.stkrep.report.item.TextEnum;

public class KarteEnumExtractor<E extends Enum<E> & TextEnum> extends AbstractKarteExtractor<E> {
    private final Class<E> clazz;

    public KarteEnumExtractor(String title, Class<E> clazz) {
        super(title);
        this.clazz = clazz;
    }

    public KarteEnumExtractor(String title, TextRange textRange, Class<E> clazz) {
        super(title, textRange);
        this.clazz = clazz;
    }

    @Override
    public E onExtract(String text) {
        return TextEnum.getEnum(clazz, text);
    }
}
