package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Medicament;

@Repository
public interface MedicamentRepository extends CrudRepository <Medicament, Long>{

}
