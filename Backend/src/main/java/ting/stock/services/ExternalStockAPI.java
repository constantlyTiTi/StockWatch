package ting.stock.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ting.stock.configuration.ExternalAPIIntegration;
import ting.stock.dto.StockDto;
import ting.stock.dto.StockPriceDto;
import ting.stock.exceptions.OverLimitedRequestsException;

import java.util.List;

@Service
@AllArgsConstructor
public class ExternalStockAPI {

    private WebClient stockExternalApi;
    private ExternalAPIIntegration externalAPIIntegration;


    public Mono<StockPriceDto> getStockBySymbol(String symbol){

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
}
