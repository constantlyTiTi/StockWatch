package ting.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockPriceDto;
import ting.stock.dto.StockWithPricesDto;
import ting.stock.services.ExternalStockAPI;
import ting.stock.services.StockService;

import java.util.List;

@RestController
@RequestMapping("api/stock")
@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RequiredArgsConstructor
public class StockController {

    final private ExternalStockAPI externalStockAPI;
    final private StockService stockService;

    @GetMapping("/quote")
    public StockPriceDto getAllStockBySymbol(@RequestParam String symbol){
        Mono<StockPriceDto> stockCurrentPrice = externalStockAPI.getStockBySymbol(symbol);
        return stockCurrentPrice.block();
    }

    @GetMapping("/symbols")
    public List<StockDto> getAllSymbolsByCountry(@RequestParam String country){
        Mono<List<StockDto>> result = externalStockAPI.getAllSymbolsByCountry(country);
        return result.block();
    }

    @GetMapping("/history_prices")
    public List<StockWithPricesDto> getAllHistoryPricesBySymbol(@RequestParam String symbol){
        List<StockWithPricesDto> result = stockService.getStockHistoryPricesInfoBySymbols(symbol);
        return result;
    }

    @GetMapping("/quotes")
    public List<StockWithPricesDto> getStocksWithPrices(){
        return stockService.getStockWithCurrentPrice();
    }

}
