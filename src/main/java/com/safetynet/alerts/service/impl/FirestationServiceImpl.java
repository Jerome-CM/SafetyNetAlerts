
package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.FirestationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationServiceImpl implements FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public FirestationDTO saveFirestation(FirestationDTO firestationDTO){
        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);
        firestationRepository.save(firestation);
        return modelMapper.map(firestation, FirestationDTO.class);
    }

    @Override
    public FirestationDTO updateFirestation(FirestationDTO firestationDTO){
        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);
        firestationRepository.save(firestation);
        return modelMapper.map(firestation, FirestationDTO.class);
    }

    // TODO Delete methode ?
    @Override
    public boolean deleteFirestation(FirestationDTO firestationDTO){
        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);
        System.out.println(firestation.getAddress());
        try {
            firestationRepository.deleteById(firestation.getId());
            return true;
        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

    @Override
    public Iterable<ArrayList> firestationCoverage(int stationNumber){


        String firestationAdresse = firestationRepository.getAddressByStation(stationNumber);
        System.out.println(firestationAdresse);
        List<ArrayList> listPersonCoverToThisStation = personRepository.getPersonWithAdresse(firestationAdresse);


        // TODO Control

        for (int i = 0; i < listPersonCoverToThisStation.size(); i++) {
            System.out.println("Personnes numéro : " + i+1);
            Person person = new Person();
            System.out.println(person.getLastName());
            System.out.println(person.getFirstName());
            System.out.println(person.getAddress());
            System.out.println(person.getPhone());
            System.out.println("-----------------------");
        }

        return listPersonCoverToThisStation;
    }
}

