package lav.valentine.soapserver.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "GetCurrencyRate")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCurrencyRateRequest {

    @XmlElement(name = "Code")
    private String code;
    @XmlElement(name = "Date")
    private String date;
}