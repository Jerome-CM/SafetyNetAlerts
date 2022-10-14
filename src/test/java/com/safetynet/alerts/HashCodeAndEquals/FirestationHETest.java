package com.safetynet.alerts.HashCodeAndEquals;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class FirestationHETest {

    @Autowired
    private ModelMapper modelMapper;

    public Firestation getFirestation(){
        Firestation firestation = new com.safetynet.alerts.model.Firestation();

        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");

        return firestation;
    }

    public FirestationDTO getFirestationDTO(){

        Firestation firestation = getFirestation();
        return modelMapper.map(firestation, FirestationDTO.class);

    }

    @Test
    public void firestationIsEqualsTest(){

        Firestation firestation1 = getFirestation();
        Firestation firestation2 = getFirestation();

        assertEquals("", true, firestation1.equals(firestation2));
        assertTrue("", firestation1.hashCode() == firestation2.hashCode());

    }

    @Test
    public void firestationDTOIsEqualsTest(){

        FirestationDTO firestation1 = getFirestationDTO();
        FirestationDTO firestation2 =  getFirestationDTO();

        assertEquals("", true, firestation1.equals(firestation2));
        assertTrue("", firestation1.hashCode() == firestation2.hashCode());

    }
}
