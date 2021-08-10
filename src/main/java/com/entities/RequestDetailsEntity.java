package com.entities;

import com.config.Config;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = Config.REQUEST_DETAILS)
public class RequestDetailsEntity {

    @Id
    private String id;
    private String acceptDate;
    private String sourceCompany;

}
