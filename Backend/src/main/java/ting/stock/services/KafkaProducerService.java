package ting.stock.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ting.stock.aop.LogAOP;
import ting.stock.configuration.ExternalAPIIntegration;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockWithPricesDto;
import ting.stock.exceptions.OverLimitedRequestsException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@LogAOP
public class KafkaProducerService {
    private final KafkaTemplate<String, List<StockDto>> kafkaProducerSymbolsTemplate;
    private final KafkaTemplate<String, List<StockWithPricesDto>> kafkaProducerStocksTemplate;
    private final TopicConfiguration topicConfiguration;
    private final ExternalStockAPI externalStockAPI;
    private final ExternalAPIIntegration externalAPIIntegration;

    public void getLatestSymbolAndPublish(String country) {
        try{
            List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry(country).block();

            kafkaProducerSymbolsTemplate.send(topicConfiguration.getSymbols().get(country),stocks);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //only get limited stocks due to api limitation
    public void getAllRealTimeStockInfoAndPublish() {
        try{
            List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry("US").block();
            assert stocks != null;
            List<String> symbols = externalAPIIntegration.getSymbols();
            if(symbols.isEmpty()){
                symbols = stocks.stream().map(StockDto::getSymbol).sorted().toList().subList(0,8);
            }
            try{
                Flux<StockWithPricesDto> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                        externalStockAPI.getStockCurrentPriceBySymbol(sy)
                                .map(response -> StockWithPricesDto.builder()
                                        .stockDto(StockDto.builder().symbol(sy).build())
                                        .stockPriceDtos(List.of(response))
                                        .build())
                );

                List<StockWithPricesDto> result = stockPriceAndInfoList.collectList().block();

                kafkaProducerStocksTemplate.send(topicConfiguration.getStock(),result);
            }catch (OverLimitedRequestsException e){
                log.info(e.getMessage());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
