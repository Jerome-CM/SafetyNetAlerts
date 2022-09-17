package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public interface PersonsRepository extends CrudRepository <Person, Long>{
	
//	@Query(value = "SELECT * FROM Persons WHERE city = :ville", nativeQuery = true)
//	public Iterable<Persons> findByCity(@Param("ville") String ville);
	
	
	public Iterable<Person> findByCity(String city);

}
