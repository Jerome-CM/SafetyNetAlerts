package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.ConsultationDTO.*;
import com.safetynet.alerts.service.interf.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Consultations {

    @Autowired
    ConsultationService consultationService;

    @GetMapping("/firestation")
    public ListFirestationDTO firestationCoverage(@RequestParam String stationNumber){
        return consultationService.firestationCoverage(stationNumber);
    }

    @GetMapping("/childAlert")
    public ListFamilyDTO childsAndOtherMembersInHouse(@RequestParam String address){
        return consultationService.childsAndOtherMembersInHouse(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhonePersonByStation(@RequestParam String firestation){
        return consultationService.getPhone(firestation);
    }

    @GetMapping("/fire")
    public FireDTO whoLivingAtThisAddress(@RequestParam String address){
        return consultationService.whoLivingAtThisAddress(address);
    }

    @GetMapping("/flood/stations")
    public List<FireDTO> stations(@RequestParam List<String> stations){
        return consultationService.stationsListPersons(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonAndMedicalsRecordDTO> personInfo(@RequestParam String firstName, String lastName){
        return consultationService.personInfo(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getMailByCity(@RequestParam String city){
        return consultationService.getMailByCity(city);
    }


}
