package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Colors {
    public Color of(java.awt.Color awtColor) {
        return new Color()
                .setRed(awtColor.getRed() / 255f)
                .setGreen(awtColor.getGreen() / 255f)
                .setBlue(awtColor.getBlue() / 255f);
    }

    public Color of(moe.pine.stkrep.report.color.Color color) {
        return new Color()
                .setRed(color.getRed())
                .setGreen(color.getGreen())
                .setBlue(color.getBlue())
                .setAlpha(color.getAlpha());
    }
}
