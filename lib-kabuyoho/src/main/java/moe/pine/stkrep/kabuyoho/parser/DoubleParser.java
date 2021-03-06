package moe.pine.stkrep.kabuyoho.parser;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.math.NumberUtils;

@UtilityClass
public class DoubleParser {
    public double parse(String text) {
        final StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (CharUtils.isAsciiNumeric(ch) || ch == '.') {
                sb.append(ch);
            }
        }

        return NumberUtils.toDouble(sb.toString(), Double.NaN);
    }
}
