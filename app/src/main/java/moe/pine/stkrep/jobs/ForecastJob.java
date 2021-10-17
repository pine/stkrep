package moe.pine.stkrep.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.Kabuyoho;
import moe.pine.stkrep.models.Forecasts;
import moe.pine.stkrep.sheets.Forecast;
import moe.pine.stkrep.sheets.ForecastSheets;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true")
@RequiredArgsConstructor
public class ForecastJob {
    private final ForecastSheets forecastSheets;
    private final Kabuyoho kabuyoho;

    @Retryable
    @Scheduled(cron = "* * * * * *")
    public void execute() throws Exception {
        // https://developers.google.com/sheets/api/quickstart/java
        // https://stackoverflow.com/questions/57972607/what-is-the-alternative-to-the-deprecated-googlecredential

        final List<String> codes = forecastSheets.getCodes();
        log.debug("Fetched codes from spreadsheets: {}", forecastSheets.getCodes());

        final List<Forecast> forecasts =
                codes.stream()
                        .map(kabuyoho::find)
                        .map(Forecasts::of)
                        .toList();

        forecastSheets.putResult(forecasts);

        Thread.sleep(60_000);
    }
}
