package ting.stock.mapper;

import org.mapstruct.Mapper;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockPriceDto;

@Mapper(componentModel = "spring")
public interface StockPriceDtoMapper {

    StockDailyPrice dtoToDao(StockPriceDto s);
    StockPriceDto daoToDto(StockDailyPrice sdp);
}
