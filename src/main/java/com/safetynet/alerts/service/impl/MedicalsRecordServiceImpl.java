package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Medicament;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.AllergiesRepository;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.MedicamentRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import com.safetynet.alerts.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalsRecordServiceImpl implements MedicalsRecordService {

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    AllergiesRepository allergiesRepository;

    @Autowired
    ModelMapper modelMapper;


    public MedicalsRecordDTO save(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        MedicalsRecord medicalsRecord = new MedicalsRecord();

        List<Person> listPerson = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
        Person person;
        if(!listPerson.isEmpty()){
            person = listPerson.get(0);

        } else {
            person = new Person();
            person.setFirstName(medicalsRecordDTO.getFirstName());
            person.setLastName(medicalsRecordDTO.getLastName());
            person = personRepository.save(person);
        }
        person.setBirthdate(medicalsRecordDTO.getBirthdate());
        medicalsRecord.setPerson(personRepository.save(person));

        for (String medicament : medicalsRecordDTO.getMedications()){
            List<Medicament> listMedicaments = medicamentRepository.findByName(medicament);

            Medicament medicamentObject;

            if(listMedicaments.isEmpty()){
               medicamentObject = medicamentRepository.save(new Medicament(medicament));
            } else {
                medicamentObject = listMedicaments.get(0);
            }

            medicalsRecord.getMedicaments().add(medicamentObject);
        }

        for (String allergie : medicalsRecordDTO.getAllergies()){
            List<Allergie> listAllergies = allergiesRepository.findByName(allergie);

            Allergie allergieObject;

            if(listAllergies.isEmpty()){
                allergieObject = allergiesRepository.save(new Allergie(allergie));
            } else {
                allergieObject = listAllergies.get(0);
            }

            medicalsRecord.getAllergies().add(allergieObject);
        }

        medicalsRecordRepository.save(medicalsRecord);
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }



    public MedicalsRecordDTO update(@RequestBody MedicalsRecordDTO medicalsRecordDTO){

        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
        MedicalsRecord medicalsRecord = new MedicalsRecord();

        if(!listPersons.isEmpty()){
            long idPerson = listPersons.get(0).getId();
            Optional<Long> idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);

            if(idMedicalsRecord.isPresent()){
                medicalsRecord.setId(idMedicalsRecord.get());
                medicalsRecord.setPerson(listPersons.get(0));

                for (String medicament : medicalsRecordDTO.getMedications()){
                    List<Medicament> listMedicaments = medicamentRepository.findByName(medicament);

                    Medicament medicamentObject;

                    if(listMedicaments.isEmpty()){
                        medicamentObject = medicamentRepository.save(new Medicament(medicament));
                    } else {
                        medicamentObject = listMedicaments.get(0);
                    }

                    medicalsRecord.getMedicaments().add(medicamentObject);
                }

                for (String allergie : medicalsRecordDTO.getAllergies()){
                    List<Allergie> listAllergies = allergiesRepository.findByName(allergie);

                    Allergie allergieObject;

                    if(listAllergies.isEmpty()){
                        allergieObject = allergiesRepository.save(new Allergie(allergie));
                    } else {
                        allergieObject = listAllergies.get(0);
                    }

                    medicalsRecord.getAllergies().add(allergieObject);
                }
                medicalsRecordRepository.save(medicalsRecord);
            }
        }
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }

    public String delete(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
        if(!listPersons.isEmpty()){
           long idPerson = listPersons.get(0).getId();
           Optional<Long> idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);
           medicalsRecordRepository.deleteById(idMedicalsRecord.get());
            return "Succes";
        } else {
            return "Error";
        }

    }

}


