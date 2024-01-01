package ting.stock.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ting.stock.configuration.TopicConfiguration;
import ting.stock.dao.Stock;
import ting.stock.dao.StockDailyPrice;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;
import ting.stock.dto.StockDto;
import ting.stock.graphqlResolvers.StockDailyPriceResolver;
import ting.stock.graphqlResolvers.StockResolver;
import ting.stock.mapper.StockConcurrentDtoMapper;
import ting.stock.mapper.StockMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConfigurationProperties
public class KafkaConsumerService {

    private final StockResolver stockResolver;
    private final StockMapper stockMapper;
    private final StockConcurrentDtoMapper stockConcurrentDtoMapper;
    private final StockDailyPriceResolver stockDailyPriceResolver;
    private final TopicConfiguration topicConfiguration;

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "#{'${topics.symbols.US}'}", groupId = "#{'{spring.kafka.group-Id-stock-info-db'}", concurrency = "3")
    public void stockSymbolConsumer(String stocksJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<StockDto> stocks = mapper.readValue(stocksJson, new TypeReference<List<StockDto>>(){});
        assert stocks != null;
        List<String> symbols = stocks.stream().map(StockDto::getSymbol).toList();

        Map<String, StockDto> stock_in = stocks.stream().collect(Collectors.toMap(StockDto::getSymbol, Function.identity()));

        Map<String, Stock> stock_db = stockResolver.queryStocksBySymbols(symbols).stream().collect(Collectors.toMap(Stock::getSymbol, Function.identity()));

        if(stock_db.size() > 0){
            List<Stock> stock_shouldBeUpdated = stock_in.entrySet().stream()
                    .filter(si -> !si.getValue().dtoValueEqualDaoValue(stock_db.get(si.getKey())))
                    .map(si-> {
                        Stock tbi = stockMapper.dtoToDao(si.getValue());
                        if(stock_db.containsKey(si.getKey())){
                            tbi.setId(stock_db.get(si.getKey()).getId());
                        }
                        return tbi;
                    })
                    .toList();
            if(Objects.nonNull(stock_shouldBeUpdated) && stock_shouldBeUpdated.size() > 0){
                stockResolver.createBulkStocks(stock_shouldBeUpdated);
            }
        }else{
            stockResolver.createBulkStocks(stocks.stream().map(stockMapper::dtoToDao).toList());
        }

    }

    @KafkaListener(topics = "#{'${topics.symbols.US}'}", groupId = "#{'{spring.kafka.group-Id-stock-info-fe'}", concurrency = "3")
    public void stockSymbolFEConsumer(String stocksJson) {
        String WEB_SOCKET_SYMBOL_TOPIC = "ws-symbols-fe";
        messagingTemplate.convertAndSend(topicConfiguration.getWebSocket().get(WEB_SOCKET_SYMBOL_TOPIC),stocksJson);
    }

    @KafkaListener(topics = "#{'${topics.stock}'}", groupId = "#{'{spring.kafka.group-Id-stock-price-db'}", concurrency = "3")
    public void stocksPricesConsumer(String stockPriceJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<StockCurrentPriceWithStockInfoDto> dtos = mapper.readValue(stockPriceJson, new TypeReference<>() {});
        List<StockDailyPrice> sdps = dtos.stream().map(d -> {
            StockDailyPrice dr = stockConcurrentDtoMapper.dtoToDao(d.getStockConcurrentPriceDtos().get(0));
            Stock sr = stockMapper.dtoToDao(d.getStockDto());
            dr.setSymbol(sr.getSymbol());
            dr.setDatetime(LocalDateTime.now());
            return dr;
        }).toList();

        stockDailyPriceResolver.createBulkStockDailyPrice(sdps);

    }

    @KafkaListener(topics = "#{'${topics.stock}'}", groupId = "#{'{spring.kafka.group-Id-stock-price-fe'}", concurrency = "3")
    public void stocksPricesFEConsumer(String stockPriceJson) {
        //send to fe
        String WEB_SOCKET_PRICES_TOPIC = "ws-prices-fe";
        messagingTemplate.convertAndSend(topicConfiguration.getWebSocket().get(WEB_SOCKET_PRICES_TOPIC),stockPriceJson);
    }

}
