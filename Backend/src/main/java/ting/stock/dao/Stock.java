package ting.stock.dao;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(name="stock")
@Table(name="stock")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="currency")
    private String currency;
    @Column(name="description")
    private String description;
    @Column(name="displaySymbol")
    private String displaySymbol;
    @Column(name="symbol",unique = true)
    private String symbol;
    @Column(name="symbol2")
    private String symbol2;
    @Column(name="figi")
    private String figi;
    @Column(name="isin")
    private String isin;
    @Column(name="mic")
    private String mic;
    @Column(name="shareClassFIGI")
    private String shareClassFIGI;
    @Column(name="type")
    private String type;

}
