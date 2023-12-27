package ting.stock.dao;

import jakarta.persistence.Entity;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
public class StockDailyPrice {
    final private Long id;
    final private float price;
    final private LocalDateTime datetime;
}
