package moe.pine.stkrep.sheets.common;

import com.google.api.services.sheets.v4.model.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Colors {
    public Color toSheetsColor(java.awt.Color awtColor) {
        return new Color()
                .setRed(awtColor.getRed() / 255f)
                .setGreen(awtColor.getGreen() / 255f)
                .setBlue(awtColor.getBlue() / 255f);
    }
}
