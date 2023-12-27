package ting.stock.model;

import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class StockDailyPriceListModel {
    private Stock stock;
    private LinkedList<StockDailyPrice> prices;
    private LocalDateTime datetime;
}
