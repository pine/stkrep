package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.internal.BaseStyler;

public interface Mapper {
    default CellData map(ForecastReport report) {
        return new CellData()
                .setUserEnteredValue(mapValue(report))
                .setUserEnteredFormat(mapFormat(new BaseStyler(report), report));
    }

    ExtendedValue mapValue(ForecastReport report);

    default CellFormat mapFormat(BaseStyler styler, ForecastReport report) {
        return styler.cellFormat();
    }
}
