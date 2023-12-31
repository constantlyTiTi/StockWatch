package ting.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockCurrentPriceWithStockInfoDto {
    private StockDto stock;
    private StockConcurrentPriceDto stockConcurrentPriceDto;
}
