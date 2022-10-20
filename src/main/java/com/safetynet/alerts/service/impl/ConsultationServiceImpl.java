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

    /**
     *
     * @param stationNumber The number of the station
     * @return {@link ListFirestationDTO} - List of Person, number childrens and adults cover by this station
     */
    @Override
    public ListFirestationDTO firestationCoverage(String stationNumber){

        logger.trace("--- Call : firestationCoverage ---");
        logger.info("Data send by User : {}",stationNumber);

        List<Firestation> listFirestationAdresse = firestationRepository.findAddressByStation(stationNumber);
        logger.info("Address finded : {}",listFirestationAdresse.get(0).getAddress());
        List<Person> listPersonCoverToThisStation = personRepository.getPersonWithAddress(listFirestationAdresse.get(0).getAddress());
        logger.info("Person cover to this station : {}",listPersonCoverToThisStation.toString());
        ListFirestationDTO formatReturn = new ListFirestationDTO();

        // Set PersonInfoDTO
        for (Person person : listPersonCoverToThisStation) {
            PersonInfoDTO habitant = new PersonInfoDTO();

            habitant.setFirstName(person.getFirstName());
            habitant.setLastName(person.getLastName());
            habitant.setAddress(person.getAddress());
            habitant.setPhone(person.getPhone());
            habitant.setAge(Utility.personAge(person.getBirthdate()));
            habitant.setMail(person.getEmail());

            // getHabitants is an ArrayList
            formatReturn.getHabitants().add(habitant);

            if (Utility.isAdult(person.getBirthdate())) {
                formatReturn.setNbrAdults(formatReturn.getNbrAdults() + 1);
            } else {
                formatReturn.setNbrEnfants(formatReturn.getNbrEnfants() + 1);
            }
        }
        logger.info("Values returned : {}",formatReturn);
        return formatReturn;
    }

    /**
     *
     * @param address The address of the family
     * @return {@link ListFamilyDTO} - A List of childrens and a list of adults in the house
     */
    @Override
    public ListFamilyDTO childsAndOtherMembersInHouse(String address){
        logger.trace("--- Call : childsAndOtherMembersInHouse ---");
        logger.info("Data send by User : {}", address);

        List<Person> listPersonToThisAddress = personRepository.getPersonWithAddress(address);

        logger.info("Persons finded : {}",listPersonToThisAddress);

        List<PersonInfoDTO> listOtherDTO = new ArrayList<>();
        List<PersonInfoDTO> listChildsDTO = new ArrayList<>();

        for (Person person : listPersonToThisAddress) {

            // Set PersonInfoDTO for List
            PersonInfoDTO habitant = new PersonInfoDTO();

            habitant.setFirstName(person.getFirstName());
            habitant.setLastName(person.getLastName());
            habitant.setAddress(person.getAddress());
            habitant.setPhone(person.getPhone());
            habitant.setAge(Utility.personAge(person.getBirthdate()));
            habitant.setMail(person.getEmail());

            /// Add a habitant to the correct list based on age
            if (Utility.isAdult(person.getBirthdate())) {
                listOtherDTO.add(habitant);
            } else {
                listChildsDTO.add(habitant);
            }
        }

        ListFamilyDTO famille = new ListFamilyDTO();

        famille.setEnfants(listChildsDTO);
        famille.setAdultes(listOtherDTO);
        logger.info("Values returned : {}", famille);

        return famille;
    }

    /**
     *
     * @param stationNumber The number of the station
     * @return A list with unique phone numbers
     */
    @Override
    public List<String> getPhone(String stationNumber){

        logger.trace("--- Call : getPhone ---");
        logger.info("Data send by User : {}", stationNumber);

        List<Firestation> listFirestation = firestationRepository.findAddressByStation(stationNumber);

        logger.info("Firestations finded : {}",listFirestation);

        List<String> addressStation = new ArrayList<>();

        for(Firestation firestation : listFirestation){
            addressStation.add(firestation.getAddress());
        }

        ArrayList<List<Person>> listPerson = new ArrayList<>();
        // Add for each address, the person in listPerson
        for(String address : addressStation ){
           listPerson.add(personRepository.getPersonWithAddress(address));
        }

        List<String> listPhone = new ArrayList<>();

        // If the phone is unknow in the list, add this
        for (List<Person> personList : listPerson) {
            for (Person person : personList) {
                if (!listPhone.contains(person.getPhone())) {
                    listPhone.add(person.getPhone());
                }
            }
        }
        logger.info("Values returned : {}", listPhone);
        return listPhone;
    }

    /**
     *
     * @param address
     * @return {@link FireDTO} - A list of Person and their medicalRecord and the end, their fire station
     */
    @Override
    public FireDTO whoLivingAtThisAddress(String address) {

        logger.trace("--- Call : whoLivingAtThisAddress ---");
        logger.info("Data send by User : {}", address);

        FireDTO fireDTO = new FireDTO();
        List<Person> listPerson = personRepository.getPersonWithAddress(address);

       logger.info("Persons finded : {}", listPerson);

        for (Person person : listPerson) {

            PersonAndMedicalsRecordDTO personDTO = new PersonAndMedicalsRecordDTO();

            personDTO.setFirstName(person.getFirstName());
            personDTO.setLastName(person.getLastName());
            personDTO.setPhone(person.getPhone());
            personDTO.setAddress(person.getAddress());
            personDTO.setAge(Utility.personAge(person.getBirthdate()));
            personDTO.setMail(person.getEmail());

            // Recovery of the medicalsRecord Id
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

            // For each address, add the firestation
            List<Firestation> listStationByAddress = firestationRepository.findByAddress(address);
            FirestationDTO firestation = new FirestationDTO();

            if(!listStationByAddress.isEmpty()){
                firestation.setAddress(address);
                firestation.setStation(listStationByAddress.get(0).getStation());
            }

            fireDTO.setFirestation(firestation);

        }
        logger.info("Values returned : {}", fireDTO);
        return fireDTO;
    }

    /**
     *
     * @param stations A list of stationNumber
     * @return {@link FireDTO} - A list of Person and their medicalRecord and the end, their fire station
     */
    @Override
    public List<FireDTO> stationsListPersons(List<String> stations){

        logger.trace("--- Call : stationsListPersons ---");
        logger.info("Data send by User : {}", stations);

        List<FireDTO> listFireDTO = new ArrayList<>();
        List<Firestation> firestations = firestationRepository.findByListStationNumber(stations);

        logger.info("Firestations finded : {}", firestations);

        for(Firestation firestation : firestations) {
            listFireDTO.add(this.whoLivingAtThisAddress(firestation.getAddress()));
            System.out.println("ADD TO LIST :" + this.whoLivingAtThisAddress(firestation.getAddress()));
        }

        logger.info("Values returned : {}", listFireDTO);
        return listFireDTO;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @return {@link PersonAndMedicalsRecordDTO} - A list of Person and their medicalRecord
     */
    @Override
    public List<PersonAndMedicalsRecordDTO> personInfo(String firstName, String lastName) {

        logger.trace("--- Call : personInfo ---");
        logger.info("Data send by User : firstname : {}, lastname : {}", firstName, lastName);

        List<PersonAndMedicalsRecordDTO> listReturn = new ArrayList<>();

        List<Person> listPersons = personRepository.getPersons(firstName, lastName);

        logger.info("Persons finded : {}", listPersons);

        if (!listPersons.isEmpty()) {

            // For each person with the same first and last name
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
        logger.info("Values returned : {}", listReturn);
        return listReturn;
    }

    /**
     *
     * @param city - The name of the city
     * @return A list with unique emails
     */
    @Override
    public List<String> getMailByCity(String city){

        logger.trace("--- Call : getMailByCity ---");
        logger.info("Data send by User : {}", city);

        List<String> listEmail = new ArrayList<String>();
        Iterable<Person> listPersons = personRepository.findByCity(city);

        logger.info("Persons finded : {}",listPersons);

        // Add to list if email not already in listEmail
        for( Person person : listPersons){
            if(!person.getEmail().isEmpty()){
                if (!listEmail.contains(person.getEmail())){
                    listEmail.add(person.getEmail());
                }
            }
        }
        logger.info("Values returned : {}", listEmail);
        return listEmail;
    }
}
