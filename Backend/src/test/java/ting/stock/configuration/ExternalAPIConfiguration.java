package ting.stock.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix="external")
@EnableConfigurationProperties
@Setter
@Getter
public class ExternalAPIConfiguration {
    private String api;
    private String token;
    private List<String> symbols;
}
