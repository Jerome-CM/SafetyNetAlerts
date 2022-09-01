package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecordsMedicaments;

@Repository
public interface MedicamentRepository extends CrudRepository <MedicalRecordsMedicaments, Long>{

}
