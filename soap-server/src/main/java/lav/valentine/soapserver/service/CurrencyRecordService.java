package lav.valentine.soapserver.service;

import lav.valentine.soapserver.entity.CurrencyRecord;
import lav.valentine.soapserver.exception.CurrencyRecordExistException;
import lav.valentine.soapserver.jaxb.GetAllRecordsResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyRecordService {

    CurrencyRecord save(String currencyName, BigDecimal rate, String nominal, String date);

    List<CurrencyRecord> getAllRecord() throws CurrencyRecordExistException;

    GetAllRecordsResponse getAllRecordAndMapToResponse() throws CurrencyRecordExistException;
}