package com.entities;

import com.config.Config;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Data
@ToString
@Entity
@Table(name = Config.ROOT)
public class RootEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private RequestDetailsEntity requestDetails;

    @OneToMany
    private List<EventEntity> events;

    public RootEntity(RequestDetailsEntity requestDetails, List<EventEntity> events) {
        this.requestDetails = requestDetails;
        this.events = events;
    }
}
