package moe.pine.stkrep.report.text;

import moe.pine.stkrep.report.color.Color;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;

public interface TextEnum {
    String getText();

    Color getColor();

    static <E extends Enum<E> & TextEnum> E getEnum(Class<E> clazz, String text) {
        final List<E> values = EnumUtils.getEnumList(clazz);
        for (E value : values) {
            if (value.getText().equals(text)) {
                return value;
            }
        }

        throw new IllegalArgumentException(
                String.format("Enum not found. [clazz=%s, text=%s]", clazz, text));
    }
}
