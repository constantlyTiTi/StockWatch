package ting.stock.graphqlResolvers;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Service;
import ting.stock.dao.Stock;
import ting.stock.repositories.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockResolver {

    private StockRepository stockRepository;

    @MutationMapping
    public Stock createStock(@Argument Stock stock){
        Optional<Stock> stock_db = stockRepository.getStockBySymbol(stock.getSymbol());
        stock_db.ifPresent(value -> stock.setId(value.getId()));
        return stockRepository.save(stock);
    }

    @MutationMapping
    public List<Stock> createBulkStocks(@Argument List<Stock> stocks){
        return stockRepository.saveAll(stocks);
    }

    @SchemaMapping
    public Optional<Stock> queryStockBySymbol(@Argument String symbol){
        return stockRepository.getStockBySymbol(symbol);
    }

    @SchemaMapping
    public List<Stock> queryStocksBySymbols(@Argument List<String> symbols){
        return stockRepository.getStocksBySymbols(symbols);
    }
}
