package lav.valentine.soapserver.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public class CurrencyRecordExistException extends CurrencyRecordException {

    public CurrencyRecordExistException(String message) {
        super(message);
    }
}