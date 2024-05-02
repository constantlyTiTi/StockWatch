package ting.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ting.stock.configuration.TopicConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(TopicConfiguration.class)
@EnableAutoConfiguration
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

}
