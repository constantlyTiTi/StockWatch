package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockConcurrentPriceDto {
    @Setter(onMethod_ = {@JsonSetter(value = "c")})
    @Getter(onMethod_ = {@JsonGetter(value = "current_price")})
    private float currentPrice;

    @Setter(onMethod_ = {@JsonSetter(value = "d")})
    @Getter(onMethod_ = {@JsonGetter(value = "change")})
    private float change;

    @Setter(onMethod_ = {@JsonSetter(value = "dp")})
    @Getter(onMethod_ = {@JsonGetter(value = "percent_change")})
    private float percentChange;

    @Setter(onMethod_ = {@JsonSetter(value = "h")})
    @Getter(onMethod_ = {@JsonGetter(value = "highest_price")})
    private float highestPrice;

    @Setter(onMethod_ = {@JsonSetter(value = "l")})
    @Getter(onMethod_ = {@JsonGetter(value = "lowest_price")})
    private float lowestPrice;

    @Setter(onMethod_ = {@JsonSetter(value = "o")})
    @Getter(onMethod_ = {@JsonGetter(value = "open_price")})
    private float openPrice;

    @Setter(onMethod_ = {@JsonSetter(value = "pc")})
    @Getter(onMethod_ = {@JsonGetter(value = "previous_closed_price")})
    private float previousClosedPrice;

    @JsonProperty("t")
    private float t;

}
