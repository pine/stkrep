package moe.pine.stkrep.kabuyoho.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Color {
    BLACK("black"),
    RED("red"),
    GREEN("green"),
    ;

    @Nullable
    public static Color of(Set<String> classNames) {
        for (Color color : values()) {
            for (String className : classNames) {
                if (className.toLowerCase().contains(color.getId())) {
                    return color;
                }
            }
        }

        return null;
    }

    private final String id;
}
