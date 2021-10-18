package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.Color;
import lombok.experimental.UtilityClass;
import moe.pine.stkrep.format.StandardColor;
import org.springframework.lang.Nullable;

@UtilityClass
public class Colors {
    public static final Color DEFAULT = toSheetsColor(StandardColor.BLACK);

    @Nullable
    public Color toSheetsColor(@Nullable StandardColor color) {
        if (color == null) {
            return null;
        }

        return new Color()
                .setRed(color.getRed())
                .setGreen(color.getGreen())
                .setBlue(color.getBlue());
    }
}
