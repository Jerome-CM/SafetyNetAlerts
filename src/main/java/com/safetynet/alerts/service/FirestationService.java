package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class FirestationService implements FirestationImpl {

	@Autowired
	private FirestationRepository firestationRepository;

	public FirestationDTO saveFirestation(FirestationDTO firestationDTO) {
		return firestationRepository.save(firestationDTO);
	}


	public FirestationDTO updateFirestation(FirestationDTO firestationDTO){
		return firestationRepository.save(firestationDTO);
	}

	public FirestationDTO deleteFirestation(FirestationDTO firestationDTO){
		return firestationRepository.delete(firestationDTO);
	}

	/*public Iterable<Person> getPersons() {
		return firestationsRepository.findAll();
	}

	/*public Optional<Person> getPersonById(long id) {
		return firestationsRepository.findById(id);
	}

	public Iterable<Person> getMailByCity(String city){
		return firestationsRepository.findByCity(city);
	}*/

}
 