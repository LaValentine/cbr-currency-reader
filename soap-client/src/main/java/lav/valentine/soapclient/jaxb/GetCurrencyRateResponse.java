package lav.valentine.soapclient.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "GetCurrencyRate")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCurrencyRateResponse {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Nominal")
    private String nominal;
    @XmlElement(name = "RateToRub")
    private String rate;
    @XmlElement(name = "Code")
    private String code;
    @XmlElement(name = "Date")
    private String date;

    public static Unmarshaller getUnmarshaller() throws JAXBException {
        return JAXBContext
                .newInstance(GetCurrencyRateResponse.class)
                .createUnmarshaller();
    }
}