package ting.stock.dao;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="company_news")
@Table(name="company_news")
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CompanyNews implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="category")
    private String category;
    @Column(name="datetime")
    private LocalDateTime datetime;
    @Column(name="headline")
    private String headline;
    @Column(name="originId",unique = true)
    private String originId;
    @Column(name="image")
    private String image;
    @Column(name="related")
    private String related;
    @Column(name="source")
    private String source;
    @Column(name="summary")
    private String summary;
    @Column(name="url")
    private String url;
}
