package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.ConsultationDTO.*;
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
    public List<Object> firestationCoverage(String stationNumber){

        List<Firestation> listFirestationAdresse = firestationRepository.findAddressByStation(stationNumber);
        List<Person> listPersonCoverToThisStation = personRepository.getPersonWithAddress(listFirestationAdresse.get(0).getAddress());

        int nbrAdult = 0;
        int nbrChildren = 0;
        FirestationDTO firestationDTO = new FirestationDTO();
        List<Object> formatReturn = new ArrayList<>();

        for (Person person : listPersonCoverToThisStation) {

            firestationDTO.setFirstName(person.getFirstName());
            firestationDTO.setLastName(person.getLastName());
            firestationDTO.setAddress(person.getAddress());
            firestationDTO.setPhone(person.getPhone());

            formatReturn.add(firestationDTO);

            if (Utility.isAdult(person.getBirthdate())) {
                nbrAdult++;
            } else {
                nbrChildren++;
            }
        }

        formatReturn.add("Adult(s) : " + nbrAdult);
        formatReturn.add("Children(s) : " + nbrChildren);

        return formatReturn;
    }

    @Override
    public List<PersonAgeDTO> childsAndOtherMembersInHouse(String address){

        List<Person> listPersonToThisAddress = personRepository.getPersonWithAddress(address);

        int nbrAdult = 0;
        int nbrChildren = 0;

        List<PersonAgeDTO> listOtherDTO = new ArrayList<>();
        List<PersonAgeDTO> listChildsDTO = new ArrayList<>();

        for (Person person : listPersonToThisAddress) {

            PersonAgeDTO PersonAgeDTO = new PersonAgeDTO();

            if (Utility.isAdult(person.getBirthdate())) {
                PersonAgeDTO.setFirstName(person.getFirstName());
                PersonAgeDTO.setLastName(person.getLastName());
                PersonAgeDTO.setAge(Utility.personAge(person.getBirthdate()));
                listOtherDTO.add(PersonAgeDTO);
                nbrAdult++;
            } else {
                PersonAgeDTO.setFirstName(person.getFirstName());
                PersonAgeDTO.setLastName(person.getLastName());
                PersonAgeDTO.setAge(Utility.personAge(person.getBirthdate()));
                listChildsDTO.add(PersonAgeDTO);
                nbrChildren++;
            }
        }

        List<PersonAgeDTO> formatReturn = new ArrayList<>();

        if(nbrChildren > 0){

            formatReturn.addAll(listChildsDTO);
            formatReturn.addAll(listOtherDTO);

            return formatReturn;
        } else {
            return formatReturn;
        }
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

        for (List<Person> personList : listPerson){
            for (Person person : personList){
                listPhone.add(person.getPhone());
            }
        }

        return listPhone;
    }

    @Override
    public ArrayList<Object> whoLivingAtThisAddress(String address) {

        List<Person> listPerson = personRepository.getPersonWithAddress(address);

        ArrayList<Object> listInfosPersons = new ArrayList<Object>();

        for (Person person : listPerson) {

            FireDTO fireDTO = new FireDTO();

            fireDTO.setNom(person.getLastName());
            fireDTO.setPhone(person.getPhone());
            fireDTO.setAge(Utility.personAge(person.getBirthdate()));

            Optional<Long> haveMedicalsRecord;
            haveMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId());

            List<String> medicamentListDTO = fireDTO.getMedications();
            List<String> allergieListDTO = fireDTO.getAllergies();

            if (haveMedicalsRecord.isPresent()) {

                List<String> medicamentListTest = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(haveMedicalsRecord.get());
                List<String> allergieListTest = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(haveMedicalsRecord.get());

                if (!medicamentListTest.isEmpty()) {
                    medicamentListDTO.addAll(medicamentListTest);
                }

                if (!allergieListTest.isEmpty()) {
                    allergieListDTO.addAll(allergieListTest);
                }

            }

            fireDTO.setFirestationNumber(firestationRepository.findByAddress(address).get(0).getStation());

            listInfosPersons.add(fireDTO);

        }

        return listInfosPersons;
    }


    @Override
    public Map<String,Object> stationsListPersons(List<String> stations){

        Map<String,Object> listInfosPersonsByAddress = new HashMap<String, Object>();

        for(String numStation : stations){

            if(!firestationRepository.findAddressByStation(numStation).isEmpty()){

                List<Person> listPerson = personRepository.getPersonWithAddress(firestationRepository.findAddressByStation(numStation).get(0).getAddress());
                List<Object> listInfosPersons = new ArrayList<>();
                String address = null;

                for(Person person : listPerson) {

                    StationDTO stationDTO = new StationDTO();

                    stationDTO.setNom(person.getLastName());
                    stationDTO.setAge(Utility.personAge(person.getBirthdate()));
                    stationDTO.setPhone(person.getPhone());

                    Optional<Long> haveMedicalsRecord;
                    haveMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId());

                    List<String> medicamentListDTO = stationDTO.getMedications();
                    List<String> allergieListDTO = stationDTO.getAllergies();

                    if (haveMedicalsRecord.isPresent()) {

                        List<String> medicamentListTest = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(haveMedicalsRecord.get());
                        List<String> allergieListTest = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(haveMedicalsRecord.get());

                        if (!medicamentListTest.isEmpty()) {
                            medicamentListDTO.addAll(medicamentListTest);
                        }

                        if (!allergieListTest.isEmpty()) {
                            allergieListDTO.addAll(allergieListTest);
                        }

                    }

                    listInfosPersons.add(stationDTO);
                }

                listInfosPersonsByAddress.put("Station " + numStation + " : " + listPerson.get(0).getAddress(), listInfosPersons);
            } else {
                logger.warn("We don't find the required station : " + numStation);
            }

        }

        return listInfosPersonsByAddress;
    }


    @Override
    public ArrayList<Object> personInfo(String firstName, String lastName) {

        ArrayList<Object> listInfosPersons = new ArrayList<>();

        List<Person> listPersons = personRepository.getPersons(firstName, lastName);

        if(!listPersons.isEmpty()){

            for (Person person : listPersons){

                PersonInfoDTO personInfoDTO = new PersonInfoDTO();

                personInfoDTO.setNom(person.getLastName());
                personInfoDTO.setAddress(person.getAddress());
                personInfoDTO.setAge(Utility.personAge(person.getBirthdate()));
                personInfoDTO.setMail(person.getEmail());

                personInfoDTO.setMedications(medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId()).get()));
                personInfoDTO.setAllergies(medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId()).get()));

                listInfosPersons.add(personInfoDTO);

            }

        }

        return listInfosPersons;
    }

    @Override
    public ArrayList<String> getMailByCity(String city){
        ArrayList<String> listEmail = new ArrayList<String>();
        Iterable<Person> listPersons = personRepository.findByCity(city);
        listPersons.forEach(person -> listEmail.add(person.getEmail()));
        logger.info("--- List Mail send ---");
        return listEmail;
    }

}
