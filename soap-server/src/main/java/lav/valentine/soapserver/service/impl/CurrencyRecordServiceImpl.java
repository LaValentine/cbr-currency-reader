package lav.valentine.soapserver.service.impl;

import lav.valentine.soapserver.entity.CurrencyRecord;
import lav.valentine.soapserver.repository.CurrencyRecordRepository;
import lav.valentine.soapserver.service.CurrencyRecordService;
import lav.valentine.soapserver.exception.CurrencyRecordExistException;
import lav.valentine.soapserver.exception.CurrencyRecordSavingException;
import lav.valentine.soapserver.jaxb.GetAllRecordsResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@Service
public class CurrencyRecordServiceImpl implements CurrencyRecordService {

    private final CurrencyRecordRepository currencyRecordRepository;
    private final String existExceptionMessage;
    private final String savingExceptionMessage;

    public CurrencyRecordServiceImpl(CurrencyRecordRepository currencyRecordRepository,
                                     @Value("${currency-record-exist-exception}") String existExceptionMessage,
                                     @Value("${currency-record-saving-exception}") String savingExceptionMessage) {
        this.currencyRecordRepository = currencyRecordRepository;
        this.existExceptionMessage = existExceptionMessage;
        this.savingExceptionMessage = savingExceptionMessage;
    }

    @Override
    public CurrencyRecord save(String currencyName, BigDecimal rate, String nominal, String date) {

        log.debug("Saving CurrencyRecord: currencyName=" + currencyName +
                "rate=" + rate + "nominal=" + nominal + "date=" + date);

        CurrencyRecord currencyRecord = CurrencyRecord.builder()
                                    .currencyName(currencyName)
                                    .rateToRub(rate)
                                    .nominal(nominal)
                                    .date(LocalDate.parse(date))
                                    .build();
        currencyRecordRepository.save(currencyRecord);

        return currencyRecordRepository.findById(currencyRecord.getId())
                .orElseThrow(() -> {
                    log.error(savingExceptionMessage);
                    return new CurrencyRecordSavingException(savingExceptionMessage);
                });
    }

    @Override
    public List<CurrencyRecord> getAllRecord() throws CurrencyRecordExistException {

        log.debug("Getting all records from currency_record table");

        List<CurrencyRecord> currencyRecords = currencyRecordRepository.findAll();

        return Optional.ofNullable(!currencyRecords.isEmpty() ? currencyRecords : null)
                .orElseThrow(() ->{
                    log.error(existExceptionMessage);
                    return new CurrencyRecordExistException(existExceptionMessage);
                });
    }

    @Override
    public GetAllRecordsResponse getAllRecordAndMapToResponse() throws CurrencyRecordExistException {
        return new GetAllRecordsResponse(
                getAllRecord().stream()
                .map(currencyRecord -> new GetAllRecordsResponse.Record(
                    currencyRecord.getCurrencyName(),
                    currencyRecord.getNominal(),
                    currencyRecord.getRateToRub(),
                    currencyRecord.getDate().toString()))
                .collect(Collectors.toList()));
    }
}