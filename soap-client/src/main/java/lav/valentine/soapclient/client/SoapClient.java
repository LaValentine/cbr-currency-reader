package lav.valentine.soapclient.client;

import lav.valentine.soapclient.jaxb.GetAllRecordsRequest;
import lav.valentine.soapclient.jaxb.GetAllRecordsResponse;
import lav.valentine.soapclient.jaxb.GetCurrencyRateRequest;
import lav.valentine.soapclient.jaxb.GetCurrencyRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.*;
import javax.xml.soap.*;

@Configuration
public class SoapClient {

    private final SOAPConnection soapConnection;
    private SOAPMessage soapMessage;
    private final String soapServerUrl;

    public SoapClient(SOAPConnection soapConnection,
                      @Value("${soap-server-url}") String soapServerUrl) {
        this.soapConnection = soapConnection;
        this.soapServerUrl = soapServerUrl;
    }

    public GetCurrencyRateResponse getCursOnDate(String date, String currency) throws Exception {

        soapMessage = MessageFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL).createMessage();
        GetCurrencyRateRequest getCurrencyRateRequest = new GetCurrencyRateRequest(currency, date);

        Marshaller marshaller = GetCurrencyRateRequest.getMarshaller();
        marshaller.marshal(getCurrencyRateRequest, soapMessage.getSOAPPart().getEnvelope().getBody());

        SOAPMessage reply = soapConnection.call(soapMessage, soapServerUrl);

        Unmarshaller unmarshaller = GetCurrencyRateResponse.getUnmarshaller();

        return (GetCurrencyRateResponse) unmarshaller.unmarshal(reply.getSOAPPart()
                .getEnvelope().getBody().getFirstChild());
    }

    public GetAllRecordsResponse getAllRecordsResponse() throws Exception {

        soapMessage = MessageFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL).createMessage();

        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();

        Marshaller marshaller = GetAllRecordsRequest.getMarshaller();
        marshaller.marshal(getAllRecordsRequest, soapMessage.getSOAPPart().getEnvelope().getBody());

        SOAPMessage reply = soapConnection.call(soapMessage, soapServerUrl);

        Unmarshaller unmarshaller = GetAllRecordsResponse.getUnmarshaller();

        return (GetAllRecordsResponse) unmarshaller.unmarshal(reply.getSOAPPart()
                .getEnvelope().getBody().getFirstChild());
    }
}