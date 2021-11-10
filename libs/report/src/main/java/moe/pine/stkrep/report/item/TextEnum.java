package moe.pine.stkrep.report.item;

import moe.pine.stkrep.report.color.Color;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;
import java.util.Set;

public interface TextEnum {
    Set<String> getInputTexts();

    String getOutputText();

    Color getColor();

    static <E extends Enum<E> & TextEnum> E getEnum(Class<E> clazz, String inputText) {
        final List<E> values = EnumUtils.getEnumList(clazz);
        for (E value : values) {
            if (value.getInputTexts().contains(inputText)) {
                return value;
            }
        }

        throw new IllegalArgumentException(
                String.format("Enum not found. [clazz=%s, input-text=%s]", clazz, inputText));
    }
}
