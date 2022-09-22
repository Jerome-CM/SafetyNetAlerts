package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

import java.util.List;

@Repository
public interface FirestationRepository extends CrudRepository <Firestation, Long>{

    public String getAddressByStation(int number);

    public List<Firestation> findByAddress(String address);

    public List<Firestation> findByStation(String number);

}
