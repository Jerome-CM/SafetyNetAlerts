package com.safetynet.alerts.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.repository.AllergiesRepository;

@Service
@Data
public class AllergieService {

	@Autowired 
	private AllergiesRepository allergieRepository;
	
	public Iterable<Allergie> getAllergies() {
		return allergieRepository.findAll();
	}
	
}
 