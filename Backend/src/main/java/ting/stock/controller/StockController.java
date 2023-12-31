package ting.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ting.stock.dto.StockConcurrentPriceDto;
import ting.stock.dto.StockDto;
import ting.stock.services.ExternalStockAPI;

import java.util.List;

@RestController
@RequestMapping("api/stock")
@RequiredArgsConstructor
public class StockController {

    final private ExternalStockAPI externalStockAPI;

    @GetMapping("/quote")
    public StockConcurrentPriceDto getAllStockBySymbol(@RequestParam String symbol){
        Mono<StockConcurrentPriceDto> stockCurrentPrice = externalStockAPI.getStockBySymbol(symbol);
        return stockCurrentPrice.block();
    }

    @GetMapping("/symbols")
    public List<StockDto> getAllSymbolsByCountry(@RequestParam String country){
        Mono<List<StockDto>> result = externalStockAPI.getAllSymbolsByCountry(country);
        return result.block();
    }
}
