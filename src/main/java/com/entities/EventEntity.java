package com.entities;

import com.config.Config;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = Config.EVENT)
public class EventEntity {

    @Id
    private String id;
    private String type;
    private int insuredId;
    @OneToMany
    private List<ProductEntity> products;

}
