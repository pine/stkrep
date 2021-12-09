package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.Color;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.Objects;

@UtilityClass
@SuppressWarnings("PMD.ReturnEmptyCollectionRatherThanNull")
public class SheetsColors {
    public Color of(moe.pine.stkrep.report.color.Color color) {
        Objects.requireNonNull(color, "color");

        return new Color()
                .setRed(color.getRed())
                .setGreen(color.getGreen())
                .setBlue(color.getBlue())
                .setAlpha(color.getAlpha());
    }

    @Nullable
    public Color ofNullable(@Nullable moe.pine.stkrep.report.color.Color color) {
        if (color != null) {
            return of(color);
        }

        return null;
    }
}
