package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.ExtendedValue;

public class DefaultValueBuilder implements ValueBuilder {
    private final ExtendedValue value = new ExtendedValue();

    @Override
    public ValueBuilder boolValue(Boolean boolValue) {
        value.setBoolValue(boolValue);
        value.setFormulaValue(null);
        value.setNumberValue(null);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder formulaValue(String formulaValue) {
        value.setBoolValue(null);
        value.setFormulaValue(formulaValue);
        value.setNumberValue(null);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder numberValue(Double numberValue) {
        value.setBoolValue(null);
        value.setFormulaValue(null);
        value.setNumberValue(numberValue);
        value.setStringValue(null);
        return this;
    }

    @Override
    public ValueBuilder stringValue(String stringValue) {
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
