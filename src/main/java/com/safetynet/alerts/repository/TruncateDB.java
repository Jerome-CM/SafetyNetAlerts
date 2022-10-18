package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface TruncateDB{

    @Query(value = "TRUNCATE tb_persons", nativeQuery = true)
    public void truncatePerson();

}