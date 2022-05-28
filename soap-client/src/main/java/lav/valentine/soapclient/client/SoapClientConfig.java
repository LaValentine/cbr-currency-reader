package lav.valentine.soapclient.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.soap.*;

@Configuration
public class SoapClientConfig {

    @Bean
    public SOAPConnection getSoapConnection () throws SOAPException {
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        return soapConnFactory.createConnection();
    }
}