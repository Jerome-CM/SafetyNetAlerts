package com.safetynet.alerts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Allergie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AllergieRepository extends CrudRepository <Allergie, Long>{
    public List<Allergie> findByName(String allergie);


}
