package ting.stock.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ting.stock.utils.LongToLocalDateTimeConverter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CompanyNewsDto {

    @JsonProperty("category")
    private String category;
    @JsonProperty("datetime")
    @JsonDeserialize(converter = LongToLocalDateTimeConverter.class)
    private LocalDateTime datetime;
    @JsonProperty("headline")
    private String headline;
    @Setter(onMethod_ = {@JsonSetter(value = "origin_id")})
    @Getter(onMethod_ = {@JsonGetter("id")})
    @JsonAlias({"origin_id", "id"})
    private String originId;
    @JsonProperty("image")
    private String image;
    @JsonProperty("related")
    private String related;
    @JsonProperty("source")
    private String source;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("url")
    private String url;
}


