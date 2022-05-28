package lav.valentine.soapserver.jaxb.cbr.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "GetCursOnDate")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCursOnDateRequest {

    @XmlElement(name = "On_date")
    String date;

    public static Marshaller getMarshaller() throws JAXBException {
        return JAXBContext
                .newInstance(GetCursOnDateRequest.class)
                .createMarshaller();
    }
}