package lav.valentine.soapserver.client;

import lav.valentine.soapserver.jaxb.cbr.request.GetCursOnDateRequest;
import lav.valentine.soapserver.jaxb.cbr.response.GetCursOnDateResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import javax.xml.transform.dom.DOMSource;

@Log4j
@Configuration
public class CbrClient {

    private final SOAPConnection soapConnection;
    private SOAPMessage soapMessage;
    private final String cbrCursInDateUrl;

    public CbrClient(SOAPConnection soapConnection,
                     @Value("${cbr-curs-on-date-url}") String cbrCursInDateUrl) {
        this.soapConnection = soapConnection;
        this.cbrCursInDateUrl = cbrCursInDateUrl;
    }

    public GetCursOnDateResponse getCursOnDateResponse(String date) throws JAXBException, SOAPException {

        log.debug("Sending request to http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx on date=" + date);

        soapMessage = MessageFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL).createMessage();
        SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();

        GetCursOnDateRequest getCursOnDateRequest = new GetCursOnDateRequest();
        getCursOnDateRequest.setDate(date);

        Marshaller marshaller = GetCursOnDateRequest.getMarshaller();
        marshaller.marshal(getCursOnDateRequest, envelope.getBody());

        log.debug("Connecting to http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx");

        SOAPMessage reply = soapConnection.call(soapMessage, cbrCursInDateUrl);

        DOMSource domSource = new DOMSource(reply.getSOAPBody()
                .getFirstChild()
                .getFirstChild()
                .getLastChild()
                .getFirstChild());

        Unmarshaller unmarshaller = GetCursOnDateResponse.getUnmarshaller();

        return (GetCursOnDateResponse) unmarshaller.unmarshal(domSource);
    }
}