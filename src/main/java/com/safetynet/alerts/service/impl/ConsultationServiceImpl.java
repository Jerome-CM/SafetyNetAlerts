package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.ConsultationDTO.*;
import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.ConsultationService;
import com.safetynet.alerts.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import org.apache.logging.log4j.Logger;


@Service
public class ConsultationServiceImpl implements ConsultationService {

    private static final Logger logger = LogManager.getLogger(ConsultationServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FirestationRepository firestationRepository;

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Override
    public ListFirestationDTO firestationCoverage(String stationNumber){

        List<Firestation> listFirestationAdresse = firestationRepository.findAddressByStation(stationNumber);
        List<Person> listPersonCoverToThisStation = personRepository.getPersonWithAddress(listFirestationAdresse.get(0).getAddress());

        ListFirestationDTO formatReturn = new ListFirestationDTO();

        for (Person person : listPersonCoverToThisStation) {
            PersonInfoDTO habitant = new PersonInfoDTO();

            habitant.setFirstName(person.getFirstName());
            habitant.setLastName(person.getLastName());
            habitant.setAddress(person.getAddress());
            habitant.setPhone(person.getPhone());
            habitant.setAge(Utility.personAge(person.getBirthdate()));
            habitant.setMail(person.getEmail());

            formatReturn.getHabitants().add(habitant);


            if (Utility.isAdult(person.getBirthdate())) {
                formatReturn.setNbrAdults(formatReturn.getNbrAdults() + 1);
            } else {
                formatReturn.setNbrEnfants(formatReturn.getNbrEnfants() + 1);
            }
        }

        return formatReturn;
    }

    @Override
    public ListFamillyDTO childsAndOtherMembersInHouse(String address){

        List<Person> listPersonToThisAddress = personRepository.getPersonWithAddress(address);

        List<PersonInfoDTO> listOtherDTO = new ArrayList<>();
        List<PersonInfoDTO> listChildsDTO = new ArrayList<>();

        for (Person person : listPersonToThisAddress) {

            PersonInfoDTO habitant = new PersonInfoDTO();

            habitant.setFirstName(person.getFirstName());
            habitant.setLastName(person.getLastName());
            habitant.setAddress(person.getAddress());
            habitant.setPhone(person.getPhone());
            habitant.setAge(Utility.personAge(person.getBirthdate()));
            habitant.setMail(person.getEmail());

            if (Utility.isAdult(person.getBirthdate())) {
                listOtherDTO.add(habitant);
            } else {
                listChildsDTO.add(habitant);
            }
        }

        ListFamillyDTO famille = new ListFamillyDTO();

        famille.setEnfants(listChildsDTO);
        famille.setAdultes(listOtherDTO);

        return famille;
    }

    @Override
    public List<String> getPhone(String stationNumber){

        List<Firestation> listFirestation = firestationRepository.findAddressByStation(stationNumber);

        List<String> addressStation = new ArrayList<>();

        for(Firestation firestation : listFirestation){
            addressStation.add(firestation.getAddress());
        }

        ArrayList<List<Person>> listPerson = new ArrayList<>();
        for(String address : addressStation ){
           listPerson.add(personRepository.getPersonWithAddress(address));
        }

        List<String> listPhone = new ArrayList<>();

        for (List<Person> personList : listPerson) {
            for (Person person : personList) {
                if (!listPhone.contains(person.getPhone())) {
                    listPhone.add(person.getPhone());
                }
            }
        }

        return listPhone;
    }

    @Override
    public FireDTO whoLivingAtThisAddress(String address) {

        FireDTO fireDTO = new FireDTO();
        List<Person> listPerson = personRepository.getPersonWithAddress(address);

       // ArrayList<Object> listInfosPersons = new ArrayList<Object>();

        for (Person person : listPerson) {

            PersonAndMedicalsRecordDTO personDTO = new PersonAndMedicalsRecordDTO();

            personDTO.setFirstName(person.getFirstName());
            personDTO.setLastName(person.getLastName());
            personDTO.setPhone(person.getPhone());
            personDTO.setAddress(person.getAddress());
            personDTO.setAge(Utility.personAge(person.getBirthdate()));
            personDTO.setMail(person.getEmail());

            Optional<Long> haveMedicalsRecord;
            haveMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId());

            List<String> medicamentListDTO = new ArrayList<>();
            List<String> allergieListDTO = new ArrayList<>();

            if (haveMedicalsRecord.isPresent()) {

                List<String> medicamentListTest = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(haveMedicalsRecord.get());
                List<String> allergieListTest = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(haveMedicalsRecord.get());

                if (!medicamentListTest.isEmpty()) {
                    medicamentListDTO.addAll(medicamentListTest);
                    personDTO.getMedicalsRecord().setMedications(medicamentListDTO);
                }

                if (!allergieListTest.isEmpty()) {
                    allergieListDTO.addAll(allergieListTest);
                    personDTO.getMedicalsRecord().setAllergies(allergieListDTO);
                }

            }

            fireDTO.getPerson().add(personDTO);

            List<Firestation> listStationByAddress = firestationRepository.findByAddress(address);
            FirestationDTO firestation = new FirestationDTO();

            if(!listStationByAddress.isEmpty()){
                firestation.setAddress(address);
                firestation.setStation(listStationByAddress.get(0).getStation());
            }

            fireDTO.setFirestation(firestation);

        }

        return fireDTO;
    }


    @Override
    public List<FireDTO> stationsListPersons(List<String> stations){

        List<FireDTO> listFireDTO = new ArrayList<>();
        List<Firestation> firestations = firestationRepository.findByListStationNumber(stations);

        for(Firestation firestation : firestations) {
            listFireDTO.add(this.whoLivingAtThisAddress(firestation.getAddress()));
        }

        return listFireDTO;
    }


    @Override
    public List<PersonAndMedicalsRecordDTO> personInfo(String firstName, String lastName) {

        List<PersonAndMedicalsRecordDTO> listReturn = new ArrayList<>();

        List<Person> listPersons = personRepository.getPersons(firstName, lastName);

        if (!listPersons.isEmpty()) {

            for (Person person : listPersons) {

                PersonAndMedicalsRecordDTO habitant = new PersonAndMedicalsRecordDTO();

                habitant.setFirstName(person.getFirstName());
                habitant.setLastName(person.getLastName());
                habitant.setAddress(person.getAddress());
                habitant.setPhone(person.getPhone());
                habitant.setAge(Utility.personAge(person.getBirthdate()));
                habitant.setMail(person.getEmail());

                List<String> medicamentListDTO = new ArrayList<>();
                List<String> allergieListDTO = new ArrayList<>();

                Optional<Long> haveMedicalsRecord;
                haveMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId());

                List<String> medicamentListTest = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(haveMedicalsRecord.get());
                List<String> allergieListTest = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(haveMedicalsRecord.get());

                if (!medicamentListTest.isEmpty()) {
                    medicamentListDTO.addAll(medicamentListTest);
                    habitant.getMedicalsRecord().setMedications(medicamentListDTO);
                }

                if (!allergieListTest.isEmpty()) {
                    allergieListDTO.addAll(allergieListTest);
                    habitant.getMedicalsRecord().setAllergies(allergieListDTO);
                }

                listReturn.add(habitant);
            }
        }
        return listReturn;
    }


    @Override
    public ArrayList<String> getMailByCity(String city){
        ArrayList<String> listEmail = new ArrayList<String>();
        Iterable<Person> listPersons = personRepository.findByCity(city);
        listPersons.forEach(person -> listEmail.add(person.getEmail()));
        return listEmail;
    }
}
