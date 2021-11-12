package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.ExtendedValue;

public interface ValueBuilder {
    ValueBuilder boolValue(Boolean boolValue);

    ValueBuilder formulaValue(String formulaValue);

    ValueBuilder numberValue(Double numberValue);

    ValueBuilder stringValue(String stringValue);

    ExtendedValue build();
}
