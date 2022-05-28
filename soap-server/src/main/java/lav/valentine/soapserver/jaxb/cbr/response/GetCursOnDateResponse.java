package lav.valentine.soapserver.jaxb.cbr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "ValuteData")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCursOnDateResponse {

    @XmlElement(name = "ValuteCursOnDate")
    private List<Valute> valutes;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Valute {
        @XmlElement(name = "Vname")
        private String name;
        @XmlElement(name = "Vnom")
        private String nominal;
        @XmlElement(name = "Vcurs")
        private String rate;
        @XmlElement(name = "VchCode")
        private String code;
    }

    public static Unmarshaller getUnmarshaller() throws JAXBException {
        return JAXBContext
                .newInstance(GetCursOnDateResponse.class)
                .createUnmarshaller();
    }
}