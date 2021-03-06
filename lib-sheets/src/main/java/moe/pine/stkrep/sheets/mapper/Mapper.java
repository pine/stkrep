package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellData;

@FunctionalInterface
public interface Mapper<R> {
    CellData map(R report);
}
