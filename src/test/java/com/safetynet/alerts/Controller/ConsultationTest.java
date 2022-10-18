package com.safetynet.alerts.Controller;

import com.safetynet.alerts.controller.Consultations;
import com.safetynet.alerts.dto.ConsultationDTO.FireDTO;
import com.safetynet.alerts.dto.ConsultationDTO.ListFamillyDTO;
import com.safetynet.alerts.dto.ConsultationDTO.ListFirestationDTO;
import com.safetynet.alerts.dto.ConsultationDTO.PersonAndMedicalsRecordDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        assertEquals("Childrens", 2, listPersonDTO.getEnfants().size());
        assertEquals("Adults", 3, listPersonDTO.getAdultes().size());

    }

    @Test
    public void getPhoneTest(){

        List<String> listPhone = consultations.getPhonePersonByStation("4");
        List<Firestation> listFirestation = firestationRepository.findAddressByStation("4");

        List<String> addressStation = new ArrayList<>();

        for(Firestation firestation : listFirestation){
            addressStation.add(firestation.getAddress());
        }

        ArrayList<List<Person>> listPerson = new ArrayList<>();
        for(String address : addressStation ){
            listPerson.add(personRepository.getPersonWithAddress(address));
        }

        List<String> listPhoneControl = new ArrayList<>();

        for (List<Person> personList : listPerson) {
            for (Person person : personList) {
                if (!listPhoneControl.contains(person.getPhone())) {
                    listPhoneControl.add(person.getPhone());
                }
            }
        }

        assertEquals("", listPhoneControl.size(), listPhone.size());

    }

    @Test
    public void whoLivingAtThisAddressTest(){

        FireDTO listPerson = consultations.whoLivingAtThisAddress("1509 Culver St");

        PersonAndMedicalsRecordDTO infoPerson = listPerson.getPerson().get(1);

        assertEquals("Lastname", "Boyd", infoPerson.getLastName());
        assertEquals("Age", 33, infoPerson.getAge());
        assertEquals("Medications number", 3, infoPerson.getMedicalsRecord().getMedications().size());
        assertEquals("Allergies number", 0, infoPerson.getMedicalsRecord().getAllergies().size());
    }

    @Test
    public void stationsListPersonsTest(){

        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        listStation.add("2");

        List<FireDTO> testList = consultations.stations(listStation);

        List<String> PersonInStationOne = new ArrayList<>();
        List<String> PersonInStationTwo = new ArrayList<>();


        if(!testList.isEmpty()){
            for(FireDTO fire : testList){
                if(Objects.equals(fire.getFirestation().getStation(), "1")){
                    for (PersonAndMedicalsRecordDTO person : fire.getPerson()){
                        PersonInStationOne.add(person.getFirstName());
                    }
                } else {
                    for (PersonAndMedicalsRecordDTO person : fire.getPerson()){
                        PersonInStationTwo.add(person.getFirstName());
                    }
                }

            }
        }

        assertEquals("", 6, PersonInStationOne.size());
        assertEquals("", 5, PersonInStationTwo.size());

    }

    @Test
    public void personInfoTest(){

        PersonAndMedicalsRecordDTO infosPerson = consultations.personInfo("John", "Boyd").get(0);

        assertEquals("", "1509 Culver St", infosPerson.getAddress());
        assertEquals("", "aznol:350mg", infosPerson.getMedicalsRecord().getMedications().get(0));
        assertEquals("", "hydrapermazol:100mg", infosPerson.getMedicalsRecord().getMedications().get(1));
        assertEquals("", "nillacilan", infosPerson.getMedicalsRecord().getAllergies().get(0));

    }

    @Test
    public void getMailTest(){

        ArrayList<String> listMail = consultations.getMailByCity("Culver");

        assertEquals("", 15, listMail.size());

    }

}
