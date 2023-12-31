package ting.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ting.stock.dao.StockDailyPrice;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockDailyPriceRepository extends JpaRepository<StockDailyPrice, UUID> {
    @Query("SELECT sdp FROM stock_daily_price sdp WHERE sdp.symbol = ?1 and sdp.datetime >= ?2 and sdp.datetime < ?3")
    List<StockDailyPrice> queryPriceBySymbolAndDateTime(String symbol, Long startDateTime, Long endDateTime);
}
