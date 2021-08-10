package com.domain;

import com.config.Config;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@ToString
@XmlRootElement(name = Config.ROOT)
public class Root {

    @XmlElement(name = Config.REQUEST_DETAILS)
    private RequestDetails RequestDetails;
    @XmlElement(name = Config.EVENT)
    private List<Event> Event;
}
