package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalsRecordRepository extends CrudRepository <MedicalsRecord, Long>{
    // @Query(value="SELECT mr FROM MedicalsRecord mr WHERE mr.person.id =?1") Jointure ??
    @Query(value="SELECT id FROM tb_medicals_records WHERE person_id =?1", nativeQuery = true)
    public List<Long> findMedicalsRecordsIdByIdPerson(long id);

}
