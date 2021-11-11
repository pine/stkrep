package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("EI_EXPOSE_REP")
public class UserEnteredValueBuilder {
    private final ExtendedValue value = new ExtendedValue();

    public UserEnteredValueBuilder boolValue(Boolean boolValue) {
        value.setBoolValue(boolValue);
        value.setFormulaValue(null);
        value.setNumberValue(null);
        return this;
    }

    public ExtendedValue build() {
        return value;
    }
}
