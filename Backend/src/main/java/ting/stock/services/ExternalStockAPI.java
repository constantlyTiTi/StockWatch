package ting.stock.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ting.stock.aop.LogAOP;
import ting.stock.configuration.ExternalAPIIntegration;
import ting.stock.dto.CompanyNewsDto;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockPriceDto;
import ting.stock.exceptions.OverLimitedRequestsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@LogAOP
public class ExternalStockAPI {

    private WebClient stockExternalApi;
    private ExternalAPIIntegration externalAPIIntegration;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Mono<StockPriceDto> getStockCurrentPriceBySymbol(String symbol){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("symbol", symbol);
        params.add("token",externalAPIIntegration.getToken());

        return stockExternalApi.get().uri(uriBuilder ->
                        uriBuilder.path("/quote").queryParams(params).build())
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)){
                        return response.bodyToMono(StockPriceDto.class);
                    }else if(response.statusCode() == HttpStatus.TOO_MANY_REQUESTS){
                        throw new OverLimitedRequestsException();
                    }else{
                        throw new RuntimeException();
                    }
                });
    }

    public Mono<List<StockDto>> getAllSymbolsByCountry(String country){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("exchange", country);
        params.add("token",externalAPIIntegration.getToken());

        return stockExternalApi.get().uri(uriBuilder ->
                        uriBuilder.path("/stock/symbol").queryParams(params).build())
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)){
                        return response.bodyToMono(new ParameterizedTypeReference<>() {
                        });
                    }else if(response.statusCode() == HttpStatus.TOO_MANY_REQUESTS){
                        throw new OverLimitedRequestsException();
                    }else{
                        throw new RuntimeException();
                    }
                });
    }

    public Mono<List<CompanyNewsDto>> getCompanyNewsBySymbol(String symbol, LocalDateTime from, LocalDateTime to) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("symbol", symbol);
        params.add("from", from.format(formatter));
        params.add("to", to.format(formatter));
        params.add("token",externalAPIIntegration.getToken());
        return stockExternalApi.get().uri(uriBuilder ->
                        uriBuilder.path("/company-news").queryParams(params).build())
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)){
                        return response.bodyToMono(new ParameterizedTypeReference<>() {
                        });
                    }else if(response.statusCode() == HttpStatus.TOO_MANY_REQUESTS){
                        throw new OverLimitedRequestsException();
                    }else{
                        throw new RuntimeException();
                    }
                });
    }
}
