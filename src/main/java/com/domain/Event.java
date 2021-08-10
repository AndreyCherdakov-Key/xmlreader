package com.domain;

import com.config.Config;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
@XmlRootElement(name = Config.EVENT)
public class Event {

    @XmlElement(name = Config.XML_ID)
    private String Id;
    @XmlElement(name = Config.XML_TYPE)
    private String Type;
    @XmlElement(name = Config.EVENT_INSURED_ID)
    private int InsuredId;
    @XmlElement(name = Config.PRODUCT)
    private List<Product> Product;

}
