package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import org.springframework.lang.Nullable;

public interface ValueBuilder {
    ValueBuilder boolValue(@Nullable Boolean boolValue);

    ValueBuilder formulaValue(@Nullable String formulaValue);

    ValueBuilder numberValue(@Nullable Double numberValue);

    ValueBuilder stringValue(@Nullable String stringValue);

    ExtendedValue build();
}
