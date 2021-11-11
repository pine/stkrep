package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractMapper<R, I> implements NewMapper<R> {
    private final Selector<R, I> selector;

    @Override
    public CellData map(R report) {
        final I item = selector.select(report);
        return new CellData()
                .setUserEnteredValue(onCreateUserEnteredValue(item, new UserEnteredValueBuilder()));
    }

    protected abstract ExtendedValue onCreateUserEnteredValue(I item, UserEnteredValueBuilder builder);
}
