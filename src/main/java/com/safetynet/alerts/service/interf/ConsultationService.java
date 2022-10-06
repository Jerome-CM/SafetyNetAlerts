package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.ConsultationDTO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ConsultationService {

    public ListFirestationDTO firestationCoverage(String stationNumber);

    public ListFamillyDTO childsAndOtherMembersInHouse(String address);

    public List<String> getPhone(String stationNumber);

    public FireDTO whoLivingAtThisAddress(String address);

    public List<FireDTO> stationsListPersons(List<String> stations);

    public List<PersonAndMedicalsRecordDTO> personInfo(String firstName, String lastName);

    public ArrayList<String> getMailByCity(String city);
}
