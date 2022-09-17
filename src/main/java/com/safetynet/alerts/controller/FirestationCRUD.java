package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FirestationCRUD {
    @Autowired
    FirestationService firestationService;

    @PostMapping("/firestation")
    public FirestationDTO addFirestation(@RequestBody FirestationDTO firestationDTO){
       return firestationService.saveFirestation(firestationDTO);
    }

    @PutMapping("/firestation")
    public FirestationDTO updateFirestation(@RequestBody FirestationDTO firestationDTO){
        return firestationService.updateFirestation(firestationDTO);
    }

    @DeleteMapping("/firestation")
    public FirestationDTO deleteFirestation(@RequestBody FirestationDTO firestationDTO){
        return firestationService.deleteFirestation(firestationDTO);
    }

}
