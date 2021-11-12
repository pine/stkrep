package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Colors {
    public Color of(moe.pine.stkrep.report.color.Color color) {
        return new Color()
                .setRed(color.getRed())
                .setGreen(color.getGreen())
                .setBlue(color.getBlue())
                .setAlpha(color.getAlpha());
    }
}
