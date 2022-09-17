package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalsRecordRepository extends CrudRepository <MedicalsRecord, Long>{

}
