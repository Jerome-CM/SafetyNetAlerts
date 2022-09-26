package com.safetynet.alerts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long>{

	public Iterable<Person> findByCity(String city);

	public Iterable<Person> findByLastName(String lastName);

	@Query(value="SELECT p FROM Person p WHERE p.firstName = ?1 AND p.lastName = ?2")
	public List<Person> getPersons(String firstName, String lastName);


	@Query(value="SELECT * FROM tb_persons WHERE address = ?1",nativeQuery = true)
	public List<Person> getPersonWithAddress(String address);
}
