package com.safetynet.alerts;

import com.safetynet.alerts.dto.ConsultationDTO.FireDTO;
import com.safetynet.alerts.dto.ConsultationDTO.PersonAgeDTO;
import com.safetynet.alerts.dto.ConsultationDTO.StationDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.ConsultationServiceImpl;
import com.safetynet.alerts.utility.Utility;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ConsultationImplTest {

    @Autowired
    private ConsultationServiceImpl consultationServiceImpl;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FirestationRepository firestationRepository;

    @Test
    public void firestationCoverageTest(){


        List<Object> testList = consultationServiceImpl.firestationCoverage("3");
        List<Person> listPersonInBDD = personRepository.getPersonWithAddress(firestationRepository.findAddressByStation("3").get(0).getAddress());

        int removeNbrChildsAndAdultsInList = testList.size() - 2;

        assertEquals("", listPersonInBDD.size(), removeNbrChildsAndAdultsInList);


    }

    @Test
    public void childsAndOtherMembersInHouseTest(){

        List<PersonAgeDTO> listPersonDTO = consultationServiceImpl.childsAndOtherMembersInHouse("1509 Culver St");

        int nbrAdult = 0;
        int nbrChildren = 0;

        for(PersonAgeDTO personAgeDTO : listPersonDTO){
            if(personAgeDTO.getAge() > 18){
                nbrAdult++;
            } else {
                nbrChildren++;
            }
        }

        assertEquals("Childrens", 1, nbrChildren);
        assertEquals("Adults", 4, nbrAdult);

    }

    @Test
    public void getPhoneTest(){

        List<String> listPhone = consultationServiceImpl.getPhone("3");

        List<Person> listPersonInBDD = personRepository.getPersonWithAddress(firestationRepository.findAddressByStation("3").get(0).getAddress());

        int nbrPhoneInBDDForThisAddress = 0;
        for(Person person : listPersonInBDD){
            if(!person.getPhone().isEmpty()){
                nbrPhoneInBDDForThisAddress++;
            }
        }

        assertEquals("", nbrPhoneInBDDForThisAddress, listPhone.size());

    }

    @Test
    public void whoLivingAtThisAddressTest(){

        ArrayList<Object> listPerson = consultationServiceImpl.whoLivingAtThisAddress("1509 Culver St");

        FireDTO infoPerson = (FireDTO) listPerson.get(4);

        assertEquals("Station number", "3", infoPerson.getFirestationNumber());
        assertEquals("Lastname", "Boyd", infoPerson.getNom());
        assertEquals("Age", 38, infoPerson.getAge());
        assertEquals("Medications number", 2, infoPerson.getMedications().size());
        assertEquals("Allergies number", 1, infoPerson.getAllergies().size());
    }

    @Test
    public void stationsListPersonsTest(){

        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        listStation.add("2");

        Map<String,Object> test = consultationServiceImpl.stationsListPersons(listStation);
        
    }

}
