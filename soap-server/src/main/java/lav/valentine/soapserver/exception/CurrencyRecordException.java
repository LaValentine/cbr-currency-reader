package lav.valentine.soapserver.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class CurrencyRecordException extends RuntimeException {

    public CurrencyRecordException(String message) {
        super(message);
    }
}