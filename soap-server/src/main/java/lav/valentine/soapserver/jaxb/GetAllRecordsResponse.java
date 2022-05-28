package lav.valentine.soapserver.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "GetAllRecords")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllRecordsResponse {

    @XmlElement(name = "Record")
    private List<Record> records;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Record {
        @XmlElement(name = "Name")
        private String currencyName;
        @XmlElement(name = "Nominal")
        private String nominal;
        @XmlElement(name = "RateToRub")
        private BigDecimal rateToRub;
        @XmlElement(name = "Date")
        private String date;
    }
}