package ting.stock.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockCurrentPriceWithStockInfoDtoMapper {

    @Mapping(target = "stockDto", source = "s")
    @Mapping(target = "stockConcurrentPriceDtos", source = "sdps")
    StockCurrentPriceWithStockInfoDto daoToDto(List<StockDailyPrice> sdps, Stock s);
}
