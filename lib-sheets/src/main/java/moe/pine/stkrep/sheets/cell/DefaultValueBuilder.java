package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import org.springframework.lang.Nullable;

public class DefaultValueBuilder implements ValueBuilder {
    private final ExtendedValue value = new ExtendedValue();

    @Override
    public ValueBuilder boolValue(@Nullable Boolean boolValue) {
        value.setBoolValue(boolValue);
        value.setFormulaValue(null);
        value.setNumberValue(null);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder formulaValue(@Nullable String formulaValue) {
        value.setBoolValue(null);
        value.setFormulaValue(formulaValue);
        value.setNumberValue(null);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder numberValue(@Nullable Double numberValue) {
        value.setBoolValue(null);
        value.setFormulaValue(null);
        value.setNumberValue(numberValue);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder stringValue(@Nullable String stringValue) {
        value.setBoolValue(null);
        value.setFormulaValue(null);
        value.setNumberValue(null);
        value.setStringValue(stringValue);
        return this;
    }

    @Override
    public ExtendedValue build() {
        return value.clone();
    }
}
