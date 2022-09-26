package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.model.Person;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public interface ConsultationService {

    public List<Person> firestationCoverage(String stationNumber);

    public List<Person> childsAndOtherMembersInHouse(String address);

    public List<String> getPhone(String stationNumber);

    public ArrayList<Object> whoLivingAtThisAddress(String address);

    public ArrayList<Object> personInfo(String lastName);
    public ArrayList<String> getMailByCity(String city);
}
