package com.safetynet.alerts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long>{
	
//	@Query(value = "SELECT * FROM Persons WHERE city = :ville", nativeQuery = true)
//	public Iterable<Persons> findByCity(@Param("ville") String ville);
	public Iterable<Person> findByCity(String city);

	// public void deleteByFirstNameAndLastName(String firstName, String lastName);

	@Query(value="DELETE FROM tb_persons WHERE first_name=?1 AND last_name=?2", nativeQuery=true)
	public void deletePerson(String firstName, String lastName);

	@Query(value="SELECT * FROM tb_persons WHERE address = ?1",nativeQuery = true)
	public List<ArrayList> getPersonWithAdresse(String address);
}
