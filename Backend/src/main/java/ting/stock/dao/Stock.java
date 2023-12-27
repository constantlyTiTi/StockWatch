package ting.stock.dao;

import jakarta.persistence.Entity;
import lombok.Builder;

@Entity
@Builder
public class Stock {
    final private String symbol;
    final private String name;

}
