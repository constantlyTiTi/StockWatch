package ting.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ting.stock.model.StockConcurrentPriceModel;
import ting.stock.model.StockModel;
import ting.stock.services.ExternalStockAPI;

import java.util.List;

@RestController
@RequestMapping("api/stock")
@RequiredArgsConstructor
public class StockController {

    final private ExternalStockAPI externalStockAPI;

    @GetMapping("/quote")
    public StockConcurrentPriceModel getAllStockBySymbol(@RequestParam String symbol){
        Mono<StockConcurrentPriceModel> stockCurrentPrice = externalStockAPI.getStockBySymbol(symbol);
        return stockCurrentPrice.block();
    }

    @GetMapping("/symbols")
    public List<StockModel> getAllSymbolsByCountry(@RequestParam String country){
        Mono<List<StockModel>> result = externalStockAPI.getAllSymbolsByCountry(country);
        return result.block();
    }
}
