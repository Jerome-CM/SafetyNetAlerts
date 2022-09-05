package com.safetynet.alerts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;

@Service
public class PersonsService {

	@Autowired 
	private PersonsRepository personRepository;
	
	public Iterable<Persons> getPersons() {
		return personRepository.findAll();
	}
	
	public Optional<Persons> getPersonById(long id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Persons> getMailByCity(String city){
		return personRepository.findByCity(city);
	}
	
	public Persons addUser(Persons user) {
		return personRepository.save(user);
	}
	
}
 