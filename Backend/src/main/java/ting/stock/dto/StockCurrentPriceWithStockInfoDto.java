package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockCurrentPriceWithStockInfoDto {
    @JsonProperty("StockDto")
    private StockDto stockDto;
    @JsonProperty("StockConcurrentPriceDtos")
    private List<StockConcurrentPriceDto> stockConcurrentPriceDtos;
}
