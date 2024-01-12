package ting.stock.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ting.stock.services.KafkaProducerService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfiguration {
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(fixedDelay = 600000)
    public void schedulePublishUSSymbols() {
        kafkaProducerService.getLatestSymbolAndPublish("US");
    }

    @Scheduled(fixedDelay = 30000)
    public void schedulePublishStocksInfo() {
        kafkaProducerService.getAllRealTimeStockInfoAndPublish();
    }
}
