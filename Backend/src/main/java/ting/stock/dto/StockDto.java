package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import ting.stock.dao.Stock;

import java.util.Objects;

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
        if(Objects.isNull(stock)) return false;
        if(Objects.nonNull(this.currency) && !this.currency.equals(stock.getCurrency())){
            return false;
        }
        if(Objects.nonNull(this.description) && !this.description.equals(stock.getDescription())){
            return false;
        }
        if(Objects.nonNull(this.displaySymbol) && !this.displaySymbol.equals(stock.getDisplaySymbol())){
            return false;
        }
        if(Objects.nonNull(this.symbol) && !this.symbol.equals(stock.getSymbol())){
            return false;
        }
        if(Objects.nonNull(this.symbol2) && !this.symbol2.equals(stock.getSymbol2())){
            return false;
        }
        if(Objects.nonNull(this.figi) && !this.figi.equals(stock.getFigi())){
            return false;
        }
        if(Objects.nonNull(this.isin) && !this.isin.equals(stock.getIsin())){
            return false;
        }
        if(Objects.nonNull(this.mic) && !this.mic.equals(stock.getMic())){
            return false;
        }
        if(Objects.nonNull(this.shareClassFIGI) && !this.shareClassFIGI.equals(stock.getShareClassFIGI())){
            return false;
        }
        if(Objects.nonNull(this.type) && !this.type.equals(stock.getType())){
            return false;
        }
        return true;
    }


}
