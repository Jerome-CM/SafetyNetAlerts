package com.safetynet.alerts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonsRepository;

@Service
public class PersonsService {

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
	
	public Person addUser(Person user) {
		return personRepository.save(user);
	}
	
}
 