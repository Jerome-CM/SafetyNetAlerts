package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Medicaments;

@Repository
public interface MedicamentRepository extends CrudRepository <Medicaments, Long>{

}
