package lav.valentine.soapserver.exception.resolver;

import lav.valentine.soapserver.exception.CurrencyRecordException;
import lombok.extern.log4j.Log4j;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

@Log4j
public class CurrencyRecordExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final QName CODE = new QName("statusCode");
    private static final QName MESSAGE = new QName("message");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        if (ex instanceof CurrencyRecordException) {
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText("400");
            detail.addFaultDetailElement(MESSAGE).addText(ex.getMessage());
        }
    }

}
