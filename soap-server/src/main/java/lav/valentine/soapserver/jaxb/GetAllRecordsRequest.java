package lav.valentine.soapserver.jaxb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlType
@XmlRootElement(name = "GetAllRecords")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllRecordsRequest {
}