package lav.valentine.soapserver.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.soap.*;

@Log4j
@Configuration
public class CbrClientConfig {

    @Bean
    public SOAPConnection getSoapConnection () throws SOAPException {
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        return soapConnFactory.createConnection();
    }
}