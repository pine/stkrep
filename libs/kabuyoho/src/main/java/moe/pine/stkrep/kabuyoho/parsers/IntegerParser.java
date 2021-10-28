package moe.pine.stkrep.kabuyoho.parsers;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.math.NumberUtils;

@UtilityClass
public class IntegerParser {
    public int parse(String text) {
        final StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (CharUtils.isAsciiNumeric(ch)) {
                sb.append(ch);
            }
        }

        return NumberUtils.toInt(sb.toString());
    }
}
