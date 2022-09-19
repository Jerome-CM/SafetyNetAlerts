
package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.interf.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
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
    public boolean deleteFirestation(@RequestBody FirestationDTO firestationDTO){
        return firestationService.deleteFirestation(firestationDTO);
    }

    @GetMapping("/firestation")
    public Iterable<ArrayList> firestationCoverage(@RequestParam int stationNumber){
        return firestationService.firestationCoverage(stationNumber);
    }

}

