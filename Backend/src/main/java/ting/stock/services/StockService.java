package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ting.stock.configuration.ExternalAPIIntegration;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockWithPricesDto;
import ting.stock.graphqlResolvers.StockDailyPriceResolver;
import ting.stock.graphqlResolvers.StockResolver;
import ting.stock.mapper.StockWithPricesDtoMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    //pending handle transaction
    private final StockResolver stockResolver;
    private final StockDailyPriceResolver stockDailyPriceResolver;
    private final StockWithPricesDtoMapper stockCurrentPriceWithStockInfoDtoMapper;
    private final ExternalStockAPI externalStockAPI;
    private final ExternalAPIIntegration externalAPIIntegration;

    public List<StockWithPricesDto> getStockHistoryPricesInfoBySymbols(String... symbols){
        List<String> symbolList = Arrays.stream(symbols).toList();
        List<Stock> stocks = stockResolver.queryStocksBySymbols(symbolList);
        Map<String,Stock> stock_map = stocks.stream().collect(Collectors.toMap(Stock::getSymbol, Function.identity()));
        Map<String,List<StockDailyPrice>> stock_history_prices = stockDailyPriceResolver.queryPricesBySymbols(symbolList)
                .stream().collect(Collectors.groupingBy(StockDailyPrice::getSymbol, HashMap::new, Collectors.toCollection(ArrayList::new)));

        List<StockWithPricesDto> result = stock_map.entrySet()
                .stream().map(sm -> {
                    Stock s = sm.getValue();
                    List<StockDailyPrice> spds = stock_history_prices.get(sm.getKey());
                    return stockCurrentPriceWithStockInfoDtoMapper.daoToDto(spds,s);
                }).toList();

        return result;

    }

    //only get limited stocks due to api limitation
    public List<StockWithPricesDto> getStockWithCurrentPrice(){
        List<StockDto> stocks = externalStockAPI.getAllSymbolsByCountry("US").block();
        assert stocks != null;
        List<String> symbols = externalAPIIntegration.getSymbols();
        if(symbols.isEmpty()){
            symbols = stocks.stream().map(StockDto::getSymbol).sorted().toList().subList(0,8);
        }
        Flux<StockWithPricesDto> stockPriceAndInfoList = Flux.fromIterable(symbols).flatMap(sy ->
                externalStockAPI.getStockCurrentPriceBySymbol(sy)
                        .map(response -> StockWithPricesDto.builder()
                                .stockDto(StockDto.builder().symbol(sy).build())
                                .stockPriceDtos(List.of(response))
                                .build())
        );

        return stockPriceAndInfoList.collectList().block();
    }

}
