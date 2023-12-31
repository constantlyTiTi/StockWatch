package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.dao.Stock;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;
import ting.stock.dto.StockDto;
import ting.stock.graphqlResolvers.StockResolver;
import ting.stock.mapper.StockMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, List<StockDto>> kafkaProducerSymbolsTemplate;
    private final KafkaTemplate<String, List<StockCurrentPriceWithStockInfoDto>> kafkaProducerStocksTemplate;
    private final TopicConfiguration topicConfiguration;
    private final ExternalStockAPI externalStockAPI;
    private final StockResolver stockResolver;
    private final StockMapper stockMapper;

    public void getLatestSymbolAndPublish(String country) {
        try{
            List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry(country).block();

            assert stocks != null;
            List<String> symbols = stocks.stream().map(StockDto::getSymbol).toList();

            Map<String, StockDto> stock_in = stocks.stream().collect(Collectors.toMap(StockDto::getSymbol,Function.identity()));

            Map<String, Stock> stock_db = stockResolver.queryStocksBySymbols(symbols).stream().collect(Collectors.toMap(Stock::getSymbol, Function.identity()));

            if(stock_db.size() > 0){
                List<Stock> stock_shouldBeUpdated = stock_in.entrySet().stream()
                        .filter(si -> si.getValue().dtoValueEqualDaoValue(stock_db.get(si.getKey())))
                        .map(si->stockMapper.dtoToDao(si.getValue()))
                        .toList();
                if(Objects.nonNull(stock_shouldBeUpdated) && stock_shouldBeUpdated.size() > 0){
                    stockResolver.createBulkStocks(stock_shouldBeUpdated);
                }
            }else{
                stockResolver.createBulkStocks(stocks.stream().map(stockMapper::dtoToDao).toList());
            }

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
            List<String> symbols = stocks.stream().map(StockDto::getSymbol).toList().subList(0,10);
            Flux<StockCurrentPriceWithStockInfoDto> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                externalStockAPI.getStockBySymbol(sy)
                        .map(response -> StockCurrentPriceWithStockInfoDto.builder()
                                .stock(StockDto.builder().symbol(sy).build())
                                .stockConcurrentPriceDto(response)
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
