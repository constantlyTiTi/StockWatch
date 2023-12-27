package ting.stock.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private final TopicConfiguration topicConfiguration;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic stockUSSymbolTopic() {
        return new NewTopic(topicConfiguration.getSymbols().get("US"),1, (short) 3);
    }

    @Bean
    public NewTopic stockCanadaSymbolTopic() {
        return new NewTopic(topicConfiguration.getSymbols().get("CA"),1, (short) 3);
    }

    @Bean NewTopic stockQuoteTopic(){
        return new NewTopic(topicConfiguration.getStock(),1, (short) 3);
    }
}
