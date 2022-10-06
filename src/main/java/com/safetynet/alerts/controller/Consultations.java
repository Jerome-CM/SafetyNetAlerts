package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.ConsultationDTO.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.ConsultationServiceImpl;
import com.safetynet.alerts.service.impl.FirestationServiceImpl;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Consultations {

    @Autowired
    ConsultationServiceImpl consultationServiceImpl;

    @Autowired
    FirestationServiceImpl firestationServiceImpl;

    @GetMapping("/firestation")
    public ListFirestationDTO firestationCoverage(@RequestParam String stationNumber){
        return consultationServiceImpl.firestationCoverage(stationNumber);
    }

    @GetMapping("/childAlert")
    public ListFamillyDTO childsAndOtherMembersInHouse(@RequestParam String address){
        return consultationServiceImpl.childsAndOtherMembersInHouse(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhonePersonByStation(@RequestParam String firestation){
        return consultationServiceImpl.getPhone(firestation);
    }

    @GetMapping("/fire")
    public FireDTO whoLivingAtThisAddress(@RequestParam String address){
        return consultationServiceImpl.whoLivingAtThisAddress(address);
    }

    @GetMapping("/flood/stations")
    public List<FireDTO> stations(@RequestParam List<String> stations){
        return consultationServiceImpl.stationsListPersons(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonAndMedicalsRecordDTO> personInfo(@RequestParam String firstName, String lastName){
        return consultationServiceImpl.personInfo(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public ArrayList<String> getMailByCity(@RequestParam String city){
        return consultationServiceImpl.getMailByCity(city);
    }


}
