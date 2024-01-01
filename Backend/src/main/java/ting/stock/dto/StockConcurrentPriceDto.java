package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockConcurrentPriceDto {

    @Setter(onMethod_ = {@JsonAlias({"c", "current_price"})})
    @Getter(onMethod_ = {@JsonGetter(value = "current_price")})
    private float currentPrice;

    @Setter(onMethod_ = {@JsonAlias({"d", "change"})})
    @Getter(onMethod_ = {@JsonGetter(value = "change")})
    private float change;

    @Setter(onMethod_ = {@JsonAlias({"dp", "percent_change"})})
    @Getter(onMethod_ = {@JsonGetter(value = "percent_change")})
    private float percentChange;

    @Setter(onMethod_ = {@JsonAlias({"h", "highest_price"})})
    @Getter(onMethod_ = {@JsonGetter(value = "highest_price")})
    private float highestPrice;

    @Setter(onMethod_ = {@JsonAlias({"l", "lowest_price"})})
    @Getter(onMethod_ = {@JsonGetter(value = "lowest_price")})
    private float lowestPrice;

    @Setter(onMethod_ = {@JsonAlias({"o", "open_price"})})
    @Getter(onMethod_ = {@JsonGetter(value = "open_price")})
    private float openPrice;

    @Setter(onMethod_ = {@JsonAlias({"pc", "previous_closed_price"})})
    @Getter(onMethod_ = {@JsonGetter(value = "previous_closed_price")})
    private float previousClosedPrice;

    @JsonProperty("t")
    private float t;

}
