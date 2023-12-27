package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.model.StockConcurrentPriceModel;
import ting.stock.model.StockCurrentPriceWithStockInfo;
import ting.stock.model.StockDailyPriceListModel;
import ting.stock.model.StockModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, List<StockModel>> kafkaProducerSymbolsTemplate;
    private final KafkaTemplate<String, List<StockCurrentPriceWithStockInfo>> kafkaProducerStocksTemplate;
    private final TopicConfiguration topicConfiguration;
    private final ExternalStockAPI externalStockAPI;


//    Map<String, Set<String>> consumedRecords = new ConcurrentHashMap<>();

    public void getLatestSymbolAndPublish(String country) {
        try{
            List<StockModel> stocks = externalStockAPI.getAllSymbolsByCountry(country).block().subList(0,20);
            kafkaProducerSymbolsTemplate.send(topicConfiguration.getSymbols().get(country),stocks);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //only get first 10 stocks due to api limitation
    public void getAllRealTimeStockInfoAndPublish() {
        try{
            List<StockModel> stocks = externalStockAPI.getAllSymbolsByCountry("US").block();
            assert stocks != null;
            List<String> symbols = stocks.stream().map(StockModel::getSymbol).toList().subList(0,20);
            Flux<StockCurrentPriceWithStockInfo> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                externalStockAPI.getStockBySymbol(sy)
                        .map(response -> StockCurrentPriceWithStockInfo.builder()
                                .stock(StockModel.builder().symbol(sy).build())
                                .stockConcurrentPriceModel(response)
                                .build())
            );

            List<StockCurrentPriceWithStockInfo> result = stockPriceAndInfoList.collectList().block();

            kafkaProducerStocksTemplate.send(topicConfiguration.getStock(),result);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
