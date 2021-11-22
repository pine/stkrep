package moe.pine.stkrep.jobs;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.Kabuyoho;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.ForecastSheets;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true")
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class ForecastJob {
    private static final Duration ACCESS_INTERVAL = Duration.ofSeconds(60L);

    private final ForecastSheets forecastSheets;
    private final Kabuyoho kabuyoho;

    @Scheduled(cron = "0 0 9,18 * * *")
    public void execute() throws Exception {
        final List<Integer> codes = forecastSheets.getCodes();
        log.info("Fetched codes from spreadsheets: {}", forecastSheets.getCodes());

        final List<ForecastReport> reports =
                codes.stream()
                        .map(code -> {
                            try {
                                Thread.sleep(ACCESS_INTERVAL.toMillis());
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                throw new RuntimeException(e);
                            }
                            return kabuyoho.find(code);
                        })
                        .toList();

        final List<String> names = reports.stream().map(ForecastReport::name).toList();
        log.info("Collected forecasts: {}", names);

        forecastSheets.updateResult(reports);
        log.info("Successfully updated spreadsheets.");
    }
}
