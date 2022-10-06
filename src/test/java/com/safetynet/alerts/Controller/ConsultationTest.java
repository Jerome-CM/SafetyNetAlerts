package com.safetynet.alerts.Controller;

import com.safetynet.alerts.controller.Consultations;
import com.safetynet.alerts.dto.ConsultationDTO.FireDTO;
import com.safetynet.alerts.dto.ConsultationDTO.ListFamillyDTO;
import com.safetynet.alerts.dto.ConsultationDTO.ListFirestationDTO;
import com.safetynet.alerts.dto.ConsultationDTO.PersonAndMedicalsRecordDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ConsultationTest {

    @Autowired
    private Consultations consultations;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    FirestationRepository firestationRepository;

    @Test
    public void firestationCoverageTest(){

        ListFirestationDTO listPersonCoverage = consultations.firestationCoverage("3");

        List<Person> listPersonInBDD = personRepository.getPersonWithAddress(firestationRepository.findAddressByStation("3").get(0).getAddress());
        assertEquals("", listPersonInBDD.size(), listPersonCoverage.getHabitants().size());

    }

    @Test
    public void childsAndOtherMembersInHouseTest(){

        ListFamillyDTO listPersonDTO = consultations.childsAndOtherMembersInHouse("1509 Culver St");

        assertEquals("Childrens", 1, listPersonDTO.getEnfants().size());
        assertEquals("Adults", 4, listPersonDTO.getAdultes().size());

    }

    @Test
    public void getPhoneTest(){

        List<String> listPhone = consultations.getPhonePersonByStation("3");
        List<Person> listPersonInBDD = personRepository.getPersonWithAddress(firestationRepository.findAddressByStation("3").get(0).getAddress());


        List<String> nbrDifferentPhoneInBDDForThisAddress = new ArrayList<>();
        for(Person person : listPersonInBDD){
            if(!person.getPhone().isEmpty()){
                if (!nbrDifferentPhoneInBDDForThisAddress.contains(person.getPhone())) {
                    nbrDifferentPhoneInBDDForThisAddress.add(person.getPhone());
                }

            }
        }

        assertEquals("", nbrDifferentPhoneInBDDForThisAddress.size(), listPhone.size());

    }

    @Test
    public void whoLivingAtThisAddressTest(){

        FireDTO listPerson = consultations.whoLivingAtThisAddress("1509 Culver St");

        PersonAndMedicalsRecordDTO infoPerson = listPerson.getPerson().get(4);

        assertEquals("Lastname", "Boyd", infoPerson.getLastName());
        assertEquals("Age", 38, infoPerson.getAge());
        assertEquals("Medications number", 2, infoPerson.getMedicalsRecord().getMedications().size());
        assertEquals("Allergies number", 1, infoPerson.getMedicalsRecord().getAllergies().size());
    }

    @Test
    public void stationsListPersonsTest(){

        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        listStation.add("2");

        List<FireDTO> testList = consultations.stations(listStation);
        FireDTO firstPersonAndStation = testList.get(0);
        FireDTO secondPersonAndStation = testList.get(1);

        assertEquals("", "1", firstPersonAndStation.getFirestation().getStation());
        assertEquals("", "2", secondPersonAndStation.getFirestation().getStation());
        assertEquals("", 1 , firstPersonAndStation.getPerson().size());
        assertEquals("", 1 , secondPersonAndStation.getPerson().size());
        assertEquals("", "Peter" , firstPersonAndStation.getPerson().get(0).getFirstName());
        assertEquals("", "Duncan" , firstPersonAndStation.getPerson().get(0).getLastName());
        assertEquals("", "Jonanathan" , secondPersonAndStation.getPerson().get(0).getFirstName());
        assertEquals("", "Marrack" , secondPersonAndStation.getPerson().get(0).getLastName());
    }

    @Test
    public void personInfoTest(){

        PersonAndMedicalsRecordDTO infosPerson = consultations.personInfo("John", "Boyd").get(0);

        assertEquals("", "1509 Culver St", infosPerson.getAddress());
        assertEquals("", "hydrapermazol:100mg", infosPerson.getMedicalsRecord().getMedications().get(0));
        assertEquals("", "aznol:350mg", infosPerson.getMedicalsRecord().getMedications().get(1));
        assertEquals("", "nillacilan", infosPerson.getMedicalsRecord().getAllergies().get(0));

    }

    @Test
    public void getMailTest(){

        ArrayList<String> listMail = consultations.getMailByCity("Culver");

        assertEquals("", 6, listMail.size());

    }

}
