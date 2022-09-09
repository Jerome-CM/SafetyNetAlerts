package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Allergies;

@Repository
public interface AllergiesRepository extends CrudRepository <Allergies, Long>{

}
