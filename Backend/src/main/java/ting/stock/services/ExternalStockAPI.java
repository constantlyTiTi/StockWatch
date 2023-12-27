package ting.stock.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ting.stock.configuration.ExternalAPIIntegration;
import ting.stock.dto.StockWithDailyPricesDto;
import ting.stock.exceptions.OverLimitedRequestsException;
import ting.stock.model.StockConcurrentPriceModel;
import ting.stock.model.StockModel;

import java.util.List;

@Service
@AllArgsConstructor
public class ExternalStockAPI {

    private WebClient stockExternalApi;
    private ExternalAPIIntegration externalAPIIntegration;


    public Mono<StockConcurrentPriceModel> getStockBySymbol(String symbol){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("symbol", symbol);
        params.add("token",externalAPIIntegration.getToken());

        return stockExternalApi.get().uri(uriBuilder ->
                        uriBuilder.path("/quote").queryParams(params).build())
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)){
                        return response.bodyToMono(StockConcurrentPriceModel.class);
                    }else if(response.statusCode() == HttpStatus.TOO_MANY_REQUESTS){
                        throw new OverLimitedRequestsException();
                    }else{
                        throw new RuntimeException();
                    }
                });
    }

    public Mono<List<StockModel>> getAllSymbolsByCountry(String country){

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
