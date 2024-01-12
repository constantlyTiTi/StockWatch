package ting.stock.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix="external")
@EnableConfigurationProperties
@Setter
@Getter
public class ExternalAPIIntegration {

    private String api;
    private String token;
    private List<String> symbols;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public WebClient stockExternalApi() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return WebClient.builder()
                .baseUrl(api)
                .defaultHeaders(builder -> builder.addAll(headers))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(config -> config
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .build();
    }

}
