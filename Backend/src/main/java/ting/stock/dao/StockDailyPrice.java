package ting.stock.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="stock_daily_price")
@Table(name="stock_daily_price")
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StockDailyPrice {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    @Column(name="currentPrice")
    private float currentPrice;
    @Column(name="datetime")
    private LocalDateTime datetime;
    @Column(name="symbol")
    private String symbol;
}
