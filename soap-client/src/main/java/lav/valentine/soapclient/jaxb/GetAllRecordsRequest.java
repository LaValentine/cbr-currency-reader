package lav.valentine.soapclient.jaxb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@XmlType
@XmlRootElement(name = "GetAllRecords")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllRecordsRequest {

    public static Marshaller getMarshaller() throws JAXBException {
        return JAXBContext
                .newInstance(GetAllRecordsRequest.class)
                .createMarshaller();
    }
}