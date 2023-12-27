package ting.stock.dto;

import org.springframework.stereotype.Component;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Component
public class StockWithDailyPricesDto {
    private Stock stock;
    private LinkedList<StockDailyPrice> prices;
    private LocalDateTime datetime;
}
