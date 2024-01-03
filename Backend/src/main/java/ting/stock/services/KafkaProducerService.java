package ting.stock.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockWithPricesDto;
import ting.stock.exceptions.OverLimitedRequestsException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class KafkaProducerService {
    private final KafkaTemplate<String, List<StockDto>> kafkaProducerSymbolsTemplate;
    private final KafkaTemplate<String, List<StockWithPricesDto>> kafkaProducerStocksTemplate;
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
            try{
                Flux<StockWithPricesDto> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                        externalStockAPI.getStockBySymbol(sy)
                                .map(response -> StockWithPricesDto.builder()
                                        .stockDto(StockDto.builder().symbol(sy).build())
                                        .stockPriceDtos(List.of(response))
                                        .build())
                );

                List<StockWithPricesDto> result = stockPriceAndInfoList.collectList().block();

                kafkaProducerStocksTemplate.send(topicConfiguration.getStock(),result);
            }catch (OverLimitedRequestsException e){
                log.log(Level.INFO, e);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
