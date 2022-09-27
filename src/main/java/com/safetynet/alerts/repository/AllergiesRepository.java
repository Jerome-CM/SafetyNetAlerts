package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Medicament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Allergie;

import java.util.List;

@Repository
public interface AllergiesRepository extends CrudRepository <Allergie, Long>{

    public List<Allergie> findByName(String allergie);

}
