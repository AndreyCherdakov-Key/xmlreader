package com.entities;

import com.config.Config;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = Config.PRODUCT)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private int price;
    private String startDate;
    private String endDate;

    public ProductEntity(String type, int price, String startDate, String endDate) {
        this.type = type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
