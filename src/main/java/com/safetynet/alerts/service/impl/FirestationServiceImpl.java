
package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.FirestationService;
import com.safetynet.alerts.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FirestationServiceImpl implements FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

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
        List<Firestation> listStationByAddress = firestationRepository.findByAddress(firestation.getAddress());

        if(!listStationByAddress.isEmpty()){
            firestation.setId(listStationByAddress.get(0).getId());
            firestation = firestationRepository.save(firestation);
        }

        return modelMapper.map(firestation, FirestationDTO.class);
    }

    @Override
    public String deleteFirestation(FirestationDTO firestationDTO){
        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);

        List<Firestation> listStationByNumber = firestationRepository.findByStation(firestation.getStation());
        List<Firestation> listStationByAddress = firestationRepository.findByAddress(firestation.getAddress());

        if(listStationByNumber.isEmpty()){
            firestationRepository.delete(listStationByAddress.get(0));
            return "Succes";
        } else {
            firestationRepository.delete(listStationByNumber.get(0));
            return "Error";
        }
    }

}

