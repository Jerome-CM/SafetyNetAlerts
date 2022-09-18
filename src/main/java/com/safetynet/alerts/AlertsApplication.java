package com.safetynet.alerts;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.service.interf.PersonService;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlertsApplication implements CommandLineRunner {
	 


	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {

		System.out.println("----------- App lunch ---------");


	/*	System.out.println("***** FindAll *****");
		Iterable<Persons> habitants = personsService.getPersons();
		
		habitants.forEach(person -> System.out.println(person.getFirstName()));
		System.out.println("***** Find Solene *****");
		Optional<Persons> MaybePersonWithId2 = personsService.getPersonById(2);
		Persons personWithId2 = MaybePersonWithId2.get();
		System.out.println(personWithId2.getFirstName());
		
		System.out.println("***** Find All Allergies *****");
		
		Iterable<Allergies> allAllergies = allergiesService.getAllAllergies();
		allAllergies.forEach(allergie -> System.out.println(allergie.getName()));
		
		
		System.out.println("***** Find All Email *****");
		
		ArrayList<String> listEmail = new ArrayList<String>();
		
		Iterable<Persons> listPersons = personsService.getPersons();
		listPersons.forEach(person -> listEmail.add(person.getEmail()));
		
		
		System.out.println("***** Find Solene Allergie *****");
		
		Optional<Persons> optPerson = personsService.getPersonById(2);
		Persons personWithIdTwo = optPerson.get();	
		
		personWithIdTwo.getAllergie().forEach(
				allergie -> System.out.println(allergie.getName()));
*/

		
		System.out.println("---------- App waiting ----------");
	}

}
