
package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.FirestationDTO;

import java.util.ArrayList;
import java.util.List;

public interface FirestationService {

	public FirestationDTO saveFirestation(FirestationDTO firestationDTO);

	public FirestationDTO updateFirestation(FirestationDTO firestationDTO);

	public boolean deleteFirestation(FirestationDTO firestationDTO);

	public Iterable<ArrayList> firestationCoverage(int stationNumber);


}

