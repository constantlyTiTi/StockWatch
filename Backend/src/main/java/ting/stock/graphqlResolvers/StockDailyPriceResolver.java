package ting.stock.graphqlResolvers;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Service;
import ting.stock.dao.StockDailyPrice;
import ting.stock.repositories.StockDailyPriceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StockDailyPriceResolver {
    private StockDailyPriceRepository stockDailyPriceRepository;

    @MutationMapping
    public StockDailyPrice createStockDailyPrice(@Argument StockDailyPrice stockDailyPrice){
        stockDailyPriceRepository.save(stockDailyPrice);
        return stockDailyPrice;
    }

    @MutationMapping
    public List<StockDailyPrice> createBulkStockDailyPrice(@Argument List<StockDailyPrice> stockDailyPrices){
        stockDailyPriceRepository.saveAll(stockDailyPrices);
        return stockDailyPrices;
    }

    @SchemaMapping
    public List<StockDailyPrice> queryPricesByDateTime (@Argument String symbol, @Argument Long startDateTime, @Argument Long endDateTime){
        return stockDailyPriceRepository.queryPriceBySymbolAndDateTime(symbol,startDateTime,endDateTime);
    }

    @SchemaMapping
    public List<StockDailyPrice> queryPricesBySymbols (@Argument List<String> symbols){
        return stockDailyPriceRepository.queryPricesBySymbols(symbols);
    }
}
