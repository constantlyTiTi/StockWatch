package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ting.stock.model.StockCurrentPriceWithStockInfo;
import ting.stock.model.StockModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    @KafkaListener(topics = "#{'${topics.symbols.US}'}", groupId = "#{'{spring.kafka.group-Id'}")
    public void stockSymbolConsumer(String stockSymbols) {

//        System.out.print(stockSymbols.get(0).getSymbol());
        System.out.println("consume: "+stockSymbols);

    }

    @KafkaListener(topics = "#{'${topics.stock}'}", groupId = "#{'{spring.kafka.group-Id'}")
    public void stockInfoConsumer(String stock) {
//        System.out.print(stock.get(0).getStockConcurrentPriceModel().getCurrentPrice());
        System.out.println("consume: "+stock);
    }

}
