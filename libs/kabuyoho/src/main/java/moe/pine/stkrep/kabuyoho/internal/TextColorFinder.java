package moe.pine.stkrep.kabuyoho.internal;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.format.ForegroundColors;
import org.springframework.lang.Nullable;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@UtilityClass
public class TextColorFinder {
    private static final Map<String, Color> NAME_TO_COLOR =
            Map.of("black", ForegroundColors.BLACK,
                    "red", ForegroundColors.RED,
                    "green", ForegroundColors.GREEN);

    public Optional<Color> find(Set<String> classNames) {
        for (Map.Entry<String, Color> entry : NAME_TO_COLOR.entrySet()) {
            for (String className : classNames) {
                final String uppercaseClassName = className.toUpperCase(Locale.ROOT);

                if (uppercaseClassName.contains(entry.getKey().toUpperCase(Locale.ROOT))) {
                    return Optional.of(entry.getValue());
                }
            }
        }

        return Optional.empty();
    }
}
