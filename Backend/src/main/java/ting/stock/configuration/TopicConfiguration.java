package ting.stock.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "topics")
public class TopicConfiguration {
    private Map<String, String> symbols;
    private String stock;
    private Map<String,String> webSocket;
}
