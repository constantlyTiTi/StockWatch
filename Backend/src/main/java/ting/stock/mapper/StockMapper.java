package ting.stock.mapper;

import org.mapstruct.Mapper;
import ting.stock.dao.Stock;
import ting.stock.dto.StockDto;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock dtoToDao(StockDto s );
    StockDto DaoToDto(Stock s);
}
