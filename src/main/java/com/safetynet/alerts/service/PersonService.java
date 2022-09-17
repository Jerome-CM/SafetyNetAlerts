package com.safetynet.alerts.service;

import java.util.Optional;

import com.safetynet.alerts.dto.PersonDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonsRepository;

@Service
@Data
public class PersonService {

	@Autowired 
	private PersonsRepository personRepository;
	
	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}
	
	public Optional<Person> getPersonById(long id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Person> getMailByCity(String city){
		return personRepository.findByCity(city);
	}
	
	public PersonDTO addPerson(PersonDTO personDTO) {
		return personRepository.save(personDTO);
	}
	
}
 