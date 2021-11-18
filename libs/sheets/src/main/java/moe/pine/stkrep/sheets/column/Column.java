package moe.pine.stkrep.sheets.column;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.RowData;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.mapper.Mapper;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;

@FunctionalInterface
public interface Column<R extends Report> {
    int MAX_NUMBER_OF_COLUMNS = 1_000;

    Mapper<R> getMapper();

    default CellData map(R report) {
        return getMapper().map(report);
    }

    static <R extends Report, C extends Enum<C> & Column<R>> List<RowData> collect(
            List<R> reports,
            Class<C> columnClazz
    ) {
        return reports.stream()
                .map(report -> {
                    final List<CellData> cells =
                            EnumUtils.getEnumList(columnClazz)
                                    .stream()
                                    .map(column -> column.map(report))
                                    .toList();

                    return new RowData().setValues(cells);
                })
                .toList();
    }
}
