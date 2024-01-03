package ting.stock.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockWithPricesDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockWithPricesDtoMapper {

    @Mapping(target = "stockDto", source = "s")
    @Mapping(target = "stockPriceDtos", source = "sdps")
    StockWithPricesDto daoToDto(List<StockDailyPrice> sdps, Stock s);
}
