package com.domain;

import com.config.Config;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement(name = Config.PRODUCT)
public class Product {

    @XmlElement(name = Config.XML_TYPE)
    private String Type;
    @XmlElement(name = Config.PRODUCT_PRICE)
    private int Price;
    @XmlElement(name = Config.PRODUCT_START_DATE)
    private String StartDate;
    @XmlElement(name = Config.PRODUCT_END_DATE)
    private String EndDate;

}
