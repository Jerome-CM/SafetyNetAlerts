package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestations;

@Repository
public interface FirestationsRepository extends CrudRepository <Firestations, Long>{

}
