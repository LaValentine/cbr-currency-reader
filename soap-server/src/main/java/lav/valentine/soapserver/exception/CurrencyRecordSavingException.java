package lav.valentine.soapserver.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public class CurrencyRecordSavingException extends CurrencyRecordException {

    public CurrencyRecordSavingException(String message) {
        super(message);
    }
}