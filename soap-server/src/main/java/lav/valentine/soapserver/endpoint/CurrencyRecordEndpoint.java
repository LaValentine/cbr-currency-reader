package lav.valentine.soapserver.endpoint;

import lav.valentine.soapserver.client.CbrClient;
import lav.valentine.soapserver.exception.CurrencyRecordExistException;
import lav.valentine.soapserver.jaxb.GetCurrencyRateRequest;
import lav.valentine.soapserver.jaxb.cbr.response.GetCursOnDateResponse;
import lav.valentine.soapserver.service.CurrencyRecordService;
import lav.valentine.soapserver.jaxb.GetCurrencyRateResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBException;
import javax.xml.soap.*;
import java.math.BigDecimal;

@Log4j
@Endpoint
public class CurrencyRecordEndpoint {

    private static final String NAMESPACE_URI = "";
    private final CbrClient cbrClient;
    private final CurrencyRecordService currencyRecordService;
    private final String existExceptionMessage;

    public CurrencyRecordEndpoint(CbrClient cbrClient,
                                  CurrencyRecordService currencyRecordService,
                                  @Value("${cbr-curs-exist-exception}") String existExceptionMessage) {
        this.cbrClient = cbrClient;
        this.currencyRecordService = currencyRecordService;
        this.existExceptionMessage = existExceptionMessage;
    }

    @PayloadRoot(localPart = "GetCurrencyRate", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetCurrencyRateResponse getCursOnDateResponse(@RequestPayload GetCurrencyRateRequest request)
            throws JAXBException, SOAPException {

        log.debug("Got request " + request);

        GetCursOnDateResponse getCursOnDateResponse = cbrClient.getCursOnDateResponse(request.getDate());
        GetCursOnDateResponse.Valute valuteRate = getCursOnDateResponse.getValutes().stream()
                .filter(valute -> valute.getCode().equals(request.getCode()))
                .findFirst().orElseThrow(() -> {
                    log.error(existExceptionMessage);
                    return new CurrencyRecordExistException(existExceptionMessage);
                });

        currencyRecordService.save(
                valuteRate.getCode(),
                new BigDecimal(valuteRate.getRate()),
                valuteRate.getNominal(),
                request.getDate());

        return new GetCurrencyRateResponse(
                valuteRate.getName(),
                valuteRate.getNominal(),
                valuteRate.getRate(),
                valuteRate.getCode(),
                request.getDate());
    }
}