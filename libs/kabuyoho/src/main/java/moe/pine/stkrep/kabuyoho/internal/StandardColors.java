package moe.pine.stkrep.kabuyoho.internal;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.format.StandardColor;
import org.springframework.lang.Nullable;

import java.util.Set;

@UtilityClass
public class StandardColors {
    @Nullable
    public StandardColor find(Set<String> classNames) {
        for (StandardColor standardColor : StandardColor.values()) {
            for (String className : classNames) {
                if (className.toUpperCase().contains(standardColor.name())) {
                    return standardColor;
                }
            }
        }

        return null;
    }
}
