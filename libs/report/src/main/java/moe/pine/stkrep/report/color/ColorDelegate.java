package moe.pine.stkrep.report.color;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;

@Value
public class ColorDelegate implements Color, Serializable {
    @Serial
    private static final long serialVersionUID = -4012448200819681898L;

    float red;
    float green;
    float blue;

    public static ColorDelegate of(String hex) {
        final java.awt.Color color = java.awt.Color.decode(hex);
        return new ColorDelegate(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }
}
