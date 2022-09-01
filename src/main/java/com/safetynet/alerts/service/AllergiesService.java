package com.safetynet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecordsAllergies;
import com.safetynet.alerts.repository.AllergiesRepository;

@Service
public class AllergiesService {

	@Autowired 
	private AllergiesRepository allergieRepository;
	
	public Iterable<MedicalRecordsAllergies> getAllAllergies() {
		return allergieRepository.findAll();
	}
	
}
 