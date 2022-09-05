package com.safetynet.alerts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Persons;

@Repository
public interface PersonsRepository extends CrudRepository <Persons, Long>{
	
//	@Query(value = "SELECT * FROM Persons WHERE city = :ville", nativeQuery = true)
//	public Iterable<Persons> findByCity(@Param("ville") String ville);
	
	
	public Iterable<Persons> findByCity(String city);

}
