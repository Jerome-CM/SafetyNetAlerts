package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;


@RestController
public class Urls {
	
	@Autowired
	private PersonsService personsService;
	
	//return all email
	
	@GetMapping("/communityEmail")
	public ArrayList<String> communityEmail(@RequestParam String city){
		
		ArrayList<String> listEmail = new ArrayList<String>();
		
		Iterable<Persons> listPersons = personsService.getMailByCity(city);
		listPersons.forEach(person -> listEmail.add(person.getEmail()));
		
		return listEmail;
		
	}
	
	
	@PostMapping("/addUser")
	public String addANewUser(@RequestParam Map<String, String> allParams) {
		
		Persons newUser = new Persons();
		newUser.setLastName(allParams.get("lastName"));
		newUser.setFirstName(allParams.get("firstName"));
		newUser.setAddress(allParams.get("address"));
		newUser.setCity(allParams.get("city"));
		newUser.setZip(33333); // Erreur avec Map String ? 
		newUser.setPhone(allParams.get("phone"));
		newUser.setEmail(allParams.get("email"));
		newUser.setBirthdate(allParams.get("birthday"));
		
		newUser = personsService.addUser(newUser);
		
		long newUserId = newUser.getId();
		
		if(newUserId != 0) {
			return "User ajouté avec succès";
		} else {
			return "Erreur à l'enregistrement de l'utilisateur";
		}
	}
}
