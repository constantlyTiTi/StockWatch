package ting.stock.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import ting.stock.model.StockCurrentPriceWithStockInfo;
import ting.stock.model.StockModel;
import ting.stock.services.ExternalStockAPI;
import ting.stock.services.KafkaProducerService;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfiguration {
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(fixedDelay = 100000)
    public void schedulePublishUSSymbols() {
        kafkaProducerService.getLatestSymbolAndPublish("US");
    }

    @Scheduled(fixedDelay = 10000)
    public void schedulePublishStocksInfo() {
        kafkaProducerService.getAllRealTimeStockInfoAndPublish();
    }
}
