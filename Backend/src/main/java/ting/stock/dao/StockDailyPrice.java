package ting.stock.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="stock_daily_price")
@Table(name="stock_daily_price")
@Builder
@AllArgsConstructor
@Getter
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
    @ManyToOne
    @JoinColumn(name = "stock_symbol", nullable = false, updatable = false, referencedColumnName = "symbol")
    private Stock stock;
}
