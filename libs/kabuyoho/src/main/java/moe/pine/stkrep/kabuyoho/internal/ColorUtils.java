package moe.pine.stkrep.kabuyoho.internal;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.kabuyoho.models.Color;
import org.springframework.lang.Nullable;

import java.util.Set;

@UtilityClass
public class ColorUtils {
    @Nullable
    public Color find(Set<String> classNames) {
        for (Color color : Color.values()) {
            for (String className : classNames) {
                if (className.toLowerCase().contains(color.getId())) {
                    return color;
                }
            }
        }

        return null;
    }
}
