package moe.pine.stkrep.sheets.forecast.internal;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.forecast.Forecast;

public interface Mapper {
    default CellData map(Forecast forecast) {
        return new CellData()
                .setUserEnteredValue(mapValue(forecast))
                .setUserEnteredFormat(mapFormat(new BaseStyler(forecast), forecast));
    }

    ExtendedValue mapValue(Forecast forecast);

    default CellFormat mapFormat(BaseStyler styler, Forecast forecast) {
        return styler.cellFormat();
    }
}
