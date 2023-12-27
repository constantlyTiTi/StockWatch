package ting.stock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockCurrentPriceWithStockInfo {
    private StockModel stock;
    private StockConcurrentPriceModel stockConcurrentPriceModel;
}
