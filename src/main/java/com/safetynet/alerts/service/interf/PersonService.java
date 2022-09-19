package com.safetynet.alerts.service.interf;

import java.util.ArrayList;
import java.util.Optional;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;


public interface PersonService {
	public PersonDTO add(PersonDTO personDTO);

	public PersonDTO update(PersonDTO personDTO);

	public void delete(PersonDTO personDTO);
	// public void deleteByFirstNameAndLastName(PersonDTO personDTO);
	//public void deletePerson(String firstName, String lastName);

	public ArrayList<String> getMailByCity(String city);
}
 