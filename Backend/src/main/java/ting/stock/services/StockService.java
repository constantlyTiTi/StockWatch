package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;
import ting.stock.graphqlResolvers.StockDailyPriceResolver;
import ting.stock.graphqlResolvers.StockResolver;
import ting.stock.mapper.StockCurrentPriceWithStockInfoDtoMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    //pending handle transaction
    private final StockResolver stockResolver;
    private final StockDailyPriceResolver stockDailyPriceResolver;
    private final StockCurrentPriceWithStockInfoDtoMapper stockCurrentPriceWithStockInfoDtoMapper;

    public List<StockCurrentPriceWithStockInfoDto> getStockInfoBySymbols(String... symbols){
        List<String> symbolList = Arrays.stream(symbols).toList();
        List<Stock> stocks = stockResolver.queryStocksBySymbols(symbolList);
        Map<String,Stock> stock_map = stocks.stream().collect(Collectors.toMap(Stock::getSymbol, Function.identity()));
        Map<String,List<StockDailyPrice>> stock_history_prices = stockDailyPriceResolver.queryPricesBySymbols(symbolList)
                .stream().collect(Collectors.groupingBy(StockDailyPrice::getSymbol, HashMap::new, Collectors.toCollection(ArrayList::new)));

        List<StockCurrentPriceWithStockInfoDto> result = stock_map.entrySet()
                .stream().map(sm -> {
                    Stock s = sm.getValue();
                    List<StockDailyPrice> spds = stock_history_prices.get(sm.getKey());
                    return stockCurrentPriceWithStockInfoDtoMapper.daoToDto(spds,s);
                }).toList();

        return result;

    }

}
