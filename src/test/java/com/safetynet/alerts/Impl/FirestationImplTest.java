package com.safetynet.alerts.Impl;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.service.interf.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class FirestationImplTest {

    @Autowired
    FirestationService firestationService;

    @Autowired
    FirestationRepository firestationRepository;

    private FirestationDTO firestationDTO;

    @BeforeEach
    public void setUpEach(){
        firestationDTO  = new FirestationDTO();
    }

    @Test
    public void saveFirestationTest(){

        firestationDTO.setAddress("TestStationAddress");
        firestationDTO.setStation("99");

        firestationService.saveFirestation(firestationDTO);

        List<Firestation> listTest = firestationRepository.findAddressByStation("99");
        assertEquals("", "TestStationAddress", listTest.get(0).getAddress());
    }

    @Test
    public void updateFirestationTest(){

        this.saveFirestationTest();

        firestationDTO.setAddress("TestStationAddress");
        firestationDTO.setStation("88");

        firestationService.updateFirestation(firestationDTO);

        List<Firestation> listTest = firestationRepository.findByAddress("TestStationAddress");

        assertEquals("", "88", listTest.get(0).getStation());

    }

    @Test
    public void deleteFirestationTest(){

        firestationDTO.setAddress("TestStationAddress");
        firestationDTO.setStation("88");

        String messageReturn = firestationService.deleteFirestation(firestationDTO);

        assertEquals("", "Delete success", messageReturn);
    }

}
