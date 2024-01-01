package ting.stock.mapper;

import org.mapstruct.Mapper;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockConcurrentPriceDto;

@Mapper(componentModel = "spring")
public interface StockConcurrentDtoMapper {

    StockDailyPrice dtoToDao(StockConcurrentPriceDto s);
    StockConcurrentPriceDto daoToDto(StockDailyPrice sdp);
}
