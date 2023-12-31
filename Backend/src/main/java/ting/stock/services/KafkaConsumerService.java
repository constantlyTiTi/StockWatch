package ting.stock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    @KafkaListener(topics = "#{'${topics.symbols.US}'}", groupId = "#{'{spring.kafka.group-Id'}")
    public void stockSymbolConsumer(String stockSymbols) {
        System.out.println("consume: "+stockSymbols);

    }

    @KafkaListener(topics = "#{'${topics.stock}'}", groupId = "#{'{spring.kafka.group-Id'}")
    public void stockInfoConsumer(String stock) {
        System.out.println("consume: "+stock);
    }

}
