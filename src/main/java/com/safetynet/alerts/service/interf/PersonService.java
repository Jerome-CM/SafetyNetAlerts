package com.safetynet.alerts.service.interf;

import java.util.Optional;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;


public interface PersonService {

	public Iterable<Person> getPersons();
	
	/*public Optional<Person> getPersonById(long id);

	public Iterable<Person> getMailByCity(String city);*/

	public PersonDTO add(PersonDTO personDTO);

	public PersonDTO update(PersonDTO personDTO);

	public void delete(PersonDTO personDTO);
	
}
 