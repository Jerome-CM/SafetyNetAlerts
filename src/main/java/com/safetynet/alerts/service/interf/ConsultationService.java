package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.ConsultationDTO.*;

import java.util.List;

public interface ConsultationService {

    public ListFirestationDTO firestationCoverage(String stationNumber);

    public ListFamilyDTO childsAndOtherMembersInHouse(String address);

    public List<String> getPhone(String stationNumber);

    public FireDTO whoLivingAtThisAddress(String address);

    public List<FireDTO> stationsListPersons(List<String> stations);

    public List<PersonAndMedicalsRecordDTO> personInfo(String firstName, String lastName);

    public List<String> getMailByCity(String city);
}
