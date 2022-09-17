package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Allergie;

@Repository
public interface AllergiesRepository extends CrudRepository <Allergie, Long>{

}
