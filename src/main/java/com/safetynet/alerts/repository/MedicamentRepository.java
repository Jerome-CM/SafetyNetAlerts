package com.safetynet.alerts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Medicament;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicamentRepository extends CrudRepository <Medicament, Long>{
    public List<Medicament> findByName(String medicament);


}
