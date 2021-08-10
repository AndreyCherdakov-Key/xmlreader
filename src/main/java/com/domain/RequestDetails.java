package com.domain;

import com.config.Config;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@ToString
@XmlRootElement(name = Config.REQUEST_DETAILS)
public class RequestDetails {

    @XmlElement(name = Config.XML_ID)
    private String Id;
    @XmlElement(name = Config.REQUEST_DETAILS_ACCEPT_DATE)
    private String AcceptDate;
    @XmlElement(name = Config.REQUEST_DETAILS_SOURCE_COMPANY)
    private String SourceCompany;


}
