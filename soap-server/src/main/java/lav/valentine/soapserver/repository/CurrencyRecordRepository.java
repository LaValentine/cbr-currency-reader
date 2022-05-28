package lav.valentine.soapserver.repository;

import lav.valentine.soapserver.entity.CurrencyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRecordRepository extends JpaRepository<CurrencyRecord, Integer> {
}