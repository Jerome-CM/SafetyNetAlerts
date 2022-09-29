package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.ConsultationDTO.PersonAgeDTO;
import com.safetynet.alerts.service.impl.ConsultationServiceImpl;
import com.safetynet.alerts.service.impl.FirestationServiceImpl;
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
    public List<Object> firestationCoverage(@RequestParam String stationNumber){
        return consultationServiceImpl.firestationCoverage(stationNumber);
    }

    @GetMapping("/childAlert")
    public List<PersonAgeDTO> childsAndOtherMembersInHouse(@RequestParam String address){
        return consultationServiceImpl.childsAndOtherMembersInHouse(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhonePersonByStation(@RequestParam String firestation){
        return consultationServiceImpl.getPhone(firestation);
    }

    @GetMapping("/fire")
    public ArrayList<Object> whoLivingAtThisAddress(@RequestParam String address){
        return consultationServiceImpl.whoLivingAtThisAddress(address);
    }

    @GetMapping("/flood/stations")
    public Map<String,Object> stations(@RequestParam List<String> stations){
        return consultationServiceImpl.stationsListPersons(stations);
    }

    @GetMapping("/personInfo")
    public ArrayList<Object> personInfo(@RequestParam String firstName,  String lastName){
        return consultationServiceImpl.personInfo(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public ArrayList<String> getMailByCity(@RequestParam String city){
        return consultationServiceImpl.getMailByCity(city);
    }


}
