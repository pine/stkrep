package moe.pine.stkrep.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class ForecastJob {
    @Retryable
    @Scheduled(cron = "* * * * * *")
    public void execute() throws InterruptedException {
        log.info("###");
        Thread.sleep(60_000);
    }
}
