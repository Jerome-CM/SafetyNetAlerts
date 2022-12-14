package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalsRecordRepository extends CrudRepository <MedicalsRecord, Long>{

    @Query(value="SELECT id FROM tb_medicals_records WHERE person_id =?1", nativeQuery = true)
    public Optional<Long> findMedicalsRecordsIdByIdPerson(long id);

    @Query(value="SELECT tb_medicaments.name FROM tb_medicals_records_medicaments " +
            "INNER JOIN tb_medicaments ON tb_medicals_records_medicaments.medicaments_id = tb_medicaments.id " +
            "WHERE tb_medicals_records_medicaments.medicals_record_id = ?1", nativeQuery = true)
    public List<String> findMedicamentsListByMedicalsRecordsId(long id);

    @Query(value="SELECT tb_allergies.name FROM tb_medicals_records_allergies " +
            "INNER JOIN tb_allergies ON tb_medicals_records_allergies.allergies_id = tb_allergies.id " +
            "WHERE tb_medicals_records_allergies.medicals_record_id = ?1", nativeQuery = true)
    public List<String> findAllergiesListByMedicalsRecordsId(long id);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table tb_medicals_records", nativeQuery = true)
    public void truncateMedicalsRecord();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table tb_allergies", nativeQuery = true)
    public void truncateAllergie();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table tb_medicaments", nativeQuery = true)
    public void truncateMedicament();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table tb_medicals_records_allergies", nativeQuery = true)
    public void truncateMedicalsRecordHasAllergie();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table tb_medicals_records_medicaments", nativeQuery = true)
    public void truncateMedicalsRecordHasMedicament();
}

