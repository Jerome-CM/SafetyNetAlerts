package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.ConsultationDTO.PersonAgeDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ConsultationService {

    public List<Object> firestationCoverage(String stationNumber);

    public List<PersonAgeDTO> childsAndOtherMembersInHouse(String address);

    public List<String> getPhone(String stationNumber);

    public ArrayList<Object> whoLivingAtThisAddress(String address);

    public Map<String,Object> stationsListPersons(List<String> stations);

    public ArrayList<Object> personInfo(String firstName, String lastName);

    public ArrayList<String> getMailByCity(String city);
}
