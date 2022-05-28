package lav.valentine.soapserver.endpoint;

import lav.valentine.soapserver.service.CurrencyRecordService;
import lav.valentine.soapserver.jaxb.GetAllRecordsRequest;
import lav.valentine.soapserver.jaxb.GetAllRecordsResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Log4j
@Endpoint
public class GetAllRecordsEndpoint {

    private static final String NAMESPACE_URI = "";
    private final CurrencyRecordService currencyRecordService;

    public GetAllRecordsEndpoint(CurrencyRecordService currencyRecordService) {
        this.currencyRecordService = currencyRecordService;
    }

    @PayloadRoot(localPart = "GetAllRecords", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllRecordsResponse getAllRecordsResponse(@RequestPayload GetAllRecordsRequest request) {

        log.debug("Got request " + request);

        return currencyRecordService.getAllRecordAndMapToResponse();
    }
}