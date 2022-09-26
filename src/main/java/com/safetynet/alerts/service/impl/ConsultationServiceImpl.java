package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Medicament;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.ConsultationService;
import com.safetynet.alerts.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FirestationRepository firestationRepository;

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Override
    public List<Person> firestationCoverage(String stationNumber){

        List<Firestation> listFirestationAdresse = firestationRepository.findAddressByStation(stationNumber);
        System.out.println("Adresse de la station : " + listFirestationAdresse.get(0).getAddress());
        List<Person> listPersonCoverToThisStation = personRepository.getPersonWithAddress(listFirestationAdresse.get(0).getAddress());

        int nbrAdult = 0;
        int nbrChildren = 0;

        /*for (int x = 0; x < listPersonCoverToThisStation.size(); x++) {

            System.out.println(listPersonCoverToThisStation.get(x).getLastName());
            System.out.println(listPersonCoverToThisStation.get(x).getFirstName());
            System.out.println(listPersonCoverToThisStation.get(x).getAddress());
            System.out.println(listPersonCoverToThisStation.get(x).getPhone());
            System.out.println(listPersonCoverToThisStation.get(x).getBirthdate());

            if(Utility.isAdult(listPersonCoverToThisStation.get(x).getBirthdate())){
                nbrAdult++;
            } else {
                nbrChildren++;
            }*/

        for (Person person : listPersonCoverToThisStation) {

            System.out.println(person.getLastName());
            System.out.println(person.getFirstName());
            System.out.println(person.getAddress());
            System.out.println(person.getPhone());
            System.out.println(person.getBirthdate());

            if (Utility.isAdult(person.getBirthdate())) {
                nbrAdult++;
            } else {
                nbrChildren++;
            }

            System.out.println("-----------------------");
        }

        System.out.println("La station couvre " + nbrAdult + " adulte(s) et " + nbrChildren + " enfant(s)");

        return listPersonCoverToThisStation;
    }
    @Override
    public List<Person> childsAndOtherMembersInHouse(String address){

        List<Person> listPersonToThisAddress = personRepository.getPersonWithAddress(address);

        int nbrAdult = 0;
        int nbrChildren = 0;
        List<Person> listAdult = new ArrayList<>();
        List<Person> listChildren = new ArrayList<>();
        for (Person person : listPersonToThisAddress) {

            if (Utility.isAdult(person.getBirthdate())) {
                listAdult.add(person);
                nbrAdult++;
            } else {
                listChildren.add(person);
                nbrChildren++;
            }
        }

        if(nbrChildren > 0){

            for ( Person child : listChildren){
                System.out.println("Last name : " + child.getLastName());
                System.out.println("First name : " + child.getFirstName());
                System.out.println("Age : " + Utility.personAge(child.getBirthdate()));
                System.out.println("-----------------------");
            }

            for ( Person adult : listAdult){
                System.out.println("Last name : " + adult.getLastName());
                System.out.println("First name : " + adult.getFirstName());
                System.out.println("-----------------------");
            }
        }


        return listPersonToThisAddress;
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
    public ArrayList<Object> whoLivingAtThisAddress(String address){

        List<Person> listPerson = personRepository.getPersonWithAddress(address);

        ArrayList<Object> listInfosPersons = new ArrayList<Object>();

        for(Person person : listPerson){

            Map<String,Object> array = new TreeMap<String,Object>();

            Optional<Long> haveMedicalsRecord;
            haveMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(person.getId());

            if(haveMedicalsRecord.isPresent()) {

                List<String> medicamentList = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(haveMedicalsRecord.get());
                List<String> allergieList = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(haveMedicalsRecord.get());

                if(!medicamentList.isEmpty()){
                    array.put("Medicaments : ", medicamentList);
                } else {
                    array.put("Medicaments : ", "[]");
                }

                if(!allergieList.isEmpty()){
                    array.put("Allergies : ", allergieList);
                } else {
                    array.put("Allergies : ", "[]");
                }

            } else {
                array.put("Medicaments : ", "[]");
                array.put("Allergies : ", "[]");
            }
            array.put("Name :",person.getLastName());
            array.put("Phone : ",person.getPhone());
            array.put("Age : " , Utility.personAge(person.getBirthdate()));


            array.put("Firestation number : ", firestationRepository.findByAddress(address).get(0).getStation());

            listInfosPersons.add(array);
        }

        return listInfosPersons;
    }


    @Override
    public ArrayList<Object> personInfo(String lastName) {

        ArrayList<Object> listInfosPersons = new ArrayList<Object>();
        return listInfosPersons;
    }

    @Override
    public ArrayList<String> getMailByCity(String city){
        ArrayList<String> listEmail = new ArrayList<String>();
        Iterable<Person> listPersons = personRepository.findByCity(city);
        listPersons.forEach(person -> listEmail.add(person.getEmail()));
        return listEmail;

    }



}
