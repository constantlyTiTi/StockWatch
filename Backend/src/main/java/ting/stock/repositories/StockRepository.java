package ting.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ting.stock.dao.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

    Optional<Stock> getStockBySymbol(String symbol);

    @Query("SELECT s FROM stock s where s.symbol IN ?1")
    List<Stock> getStocksBySymbols(List<String> symbols);
}
