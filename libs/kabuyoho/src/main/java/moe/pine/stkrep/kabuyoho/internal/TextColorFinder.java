package moe.pine.stkrep.kabuyoho.internal;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.format.ForegroundColors;
import org.springframework.lang.Nullable;

import java.awt.Color;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class TextColorFinder {
    private static final Map<String, Color> NAME_TO_COLOR =
            Map.of("black", ForegroundColors.BLACK,
                    "red", ForegroundColors.RED,
                    "green", ForegroundColors.GREEN);

    @Nullable
    public Color find(Set<String> classNames) {
        for (Map.Entry<String, Color> entry : NAME_TO_COLOR.entrySet()) {
            for (String className : classNames) {
                if (className.toUpperCase().contains(entry.getKey().toUpperCase())) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }
}
