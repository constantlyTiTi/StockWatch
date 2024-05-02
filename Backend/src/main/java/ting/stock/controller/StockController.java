package ting.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ting.stock.dto.CompanyNewsDto;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockPriceDto;
import ting.stock.dto.StockWithPricesDto;
import ting.stock.services.ExternalStockAPI;
import ting.stock.services.StockService;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/stock")
@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RequiredArgsConstructor
public class StockController {

    final private ExternalStockAPI externalStockAPI;
    final private StockService stockService;

    @GetMapping("/quote")
    public ResponseEntity<StockPriceDto> getAllStockBySymbol(@RequestParam String symbol){
        try{
            Mono<StockPriceDto> stockCurrentPrice = externalStockAPI.getStockCurrentPriceBySymbol(symbol);
            return ResponseEntity.ok(stockCurrentPrice.block());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/symbols")
    public ResponseEntity<List<StockDto>> getAllSymbolsByCountry(@RequestParam String country){
        try{
            Mono<List<StockDto>> result = externalStockAPI.getAllSymbolsByCountry(country);
            return ResponseEntity.ok(result.block());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }

    @GetMapping("/history_prices")
    public ResponseEntity<List<StockWithPricesDto>> getAllHistoryPricesBySymbol(@RequestParam String symbol){

        try{
            List<StockWithPricesDto> result = stockService.getStockHistoryPricesInfoBySymbols(symbol);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<StockWithPricesDto>> getStocksWithPrices(){
        try{
            List<StockWithPricesDto> result = stockService.getStockWithCurrentPrice();
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/company_news")
    public ResponseEntity<List<CompanyNewsDto>> getCompanyNewsBySymbol(@RequestParam String symbol,
                                                       @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy/MM/dd") Date from,
                                                       @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy/MM/dd") Date to){

        try{
            List<CompanyNewsDto> result = externalStockAPI.getCompanyNewsBySymbol(symbol,
                    from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).block();
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

}
