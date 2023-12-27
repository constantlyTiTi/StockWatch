package ting.stock.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StockModel {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("description")
    private String description;
    @Setter(onMethod_ = {@JsonSetter(value = "display_symbol")})
    @Getter(onMethod_ = {@JsonGetter("displaySymbol")})
    @JsonAlias({"display_symbol", "displaySymbol"})
    private String displaySymbol;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("symbol2")
    private String symbol2;
    @JsonProperty("figi")
    private String figi;
    @JsonProperty("isin")
    private String isin;
    @JsonProperty("mic")
    private String mic;
    @JsonProperty("shareClassFIGI")
    @Setter(onMethod_ = {@JsonSetter(value = "share_class_figi")})
    @Getter(onMethod_ = {@JsonGetter("shareClassFIGI")})
    private String shareClassFIGI;
    @JsonProperty("type")
    private String type;


}
