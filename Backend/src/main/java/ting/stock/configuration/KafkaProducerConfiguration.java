package ting.stock.configuration;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ting.stock.dto.StockCurrentPriceWithStockInfoDto;
import ting.stock.dto.StockDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 10485760);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, List<StockDto>> kafkaProducerSymbolsTemplate() {
        return new KafkaTemplate<String,List<StockDto>>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, List<StockCurrentPriceWithStockInfoDto>> kafkaProducerStocksTemplate() {
        return new KafkaTemplate<String,List<StockCurrentPriceWithStockInfoDto>>(producerFactory());
    }
}
