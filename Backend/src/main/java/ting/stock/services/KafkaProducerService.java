package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;
import ting.stock.dto.StockDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, List<StockDto>> kafkaProducerSymbolsTemplate;
    private final KafkaTemplate<String, List<StockCurrentPriceWithStockInfoDto>> kafkaProducerStocksTemplate;
    private final TopicConfiguration topicConfiguration;
    private final ExternalStockAPI externalStockAPI;

    public void getLatestSymbolAndPublish(String country) {
        try{
            List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry(country).block();

            kafkaProducerSymbolsTemplate.send(topicConfiguration.getSymbols().get(country),stocks);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //only get first 10 stocks due to api limitation
    public void getAllRealTimeStockInfoAndPublish() {
        try{
            List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry("US").block();
            assert stocks != null;
            List<String> symbols = stocks.stream().map(StockDto::getSymbol).sorted().toList().subList(0,10);
            Flux<StockCurrentPriceWithStockInfoDto> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                externalStockAPI.getStockBySymbol(sy)
                        .map(response -> StockCurrentPriceWithStockInfoDto.builder()
                                .stockDto(StockDto.builder().symbol(sy).build())
                                .stockConcurrentPriceDtos(List.of(response))
                                .build())
            );

            List<StockCurrentPriceWithStockInfoDto> result = stockPriceAndInfoList.collectList().block();

            kafkaProducerStocksTemplate.send(topicConfiguration.getStock(),result);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
