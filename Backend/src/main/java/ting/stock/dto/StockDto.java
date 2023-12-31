package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import ting.stock.dao.Stock;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StockDto {
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

    public boolean dtoValueEqualDaoValue(Stock stock){
        return this.currency.equals(stock.getCurrency()) &&
                this.description.equals(stock.getDescription()) &&
                this.displaySymbol.equals(stock.getDisplaySymbol()) &&
                this.symbol.equals(stock.getSymbol()) &&
                this.symbol2.equals(stock.getSymbol2()) &&
                this.figi.equals(stock.getFigi()) &&
                this.isin.equals(stock.getIsin()) &&
                this.mic.equals(stock.getMic()) &&
                this.shareClassFIGI.equals(stock.getShareClassFIGI()) &&
                this.type.equals(stock.getType());
    }


}
