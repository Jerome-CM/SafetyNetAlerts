package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Medicament;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.AllergieRepository;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.MedicamentRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalsRecordServiceImpl implements MedicalsRecordService {

    private static final Logger logger = LogManager.getLogger(MedicalsRecordServiceImpl.class);

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    AllergieRepository allergieRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     *
     * @param medicalsRecordDTO
     * @return {@link MedicalsRecordDTO}
     */
    @Override
    public MedicalsRecordDTO save(@RequestBody MedicalsRecordDTO medicalsRecordDTO){

        logger.trace("--- Call : save ( a new Medical Record ) ---");
        logger.info("Data send by User : {}", medicalsRecordDTO);

        MedicalsRecord medicalsRecord = new MedicalsRecord();


        List<Person> listPerson = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());

        Person person;

        // This person exist in BDD, else, we create this person and we added in BDD
        if(!listPerson.isEmpty()){
            person = listPerson.get(0);
            logger.info("This person is finded in BDD {}", person);
        } else {
            logger.info("This person isn't finded in BDD");
            person = new Person();
            person.setFirstName(medicalsRecordDTO.getFirstName());
            person.setLastName(medicalsRecordDTO.getLastName());
            logger.info("Save in BDD with first and lastname : {}, {}",medicalsRecordDTO.getFirstName(),medicalsRecordDTO.getLastName());
            try{
                person = personRepository.save(person);
                logger.info("--- New User --- : {} ", person);
            } catch (Exception e){
                logger.error("Impossible to saved a new person in BDD : {}", e.getMessage());
            }
        }

        // Add your date of birth
        person.setBirthdate(medicalsRecordDTO.getBirthdate());

        try{
            medicalsRecord.setPerson(personRepository.save(person));
            logger.info("The birthdate saved : {}", person);
        } catch(Exception e){
            logger.error("Impossible to update a birthdate into BDD : {}", e.getMessage());
        }

        // For the medications indicated in the DTO, for each medicament, we check it if exist in BDD, else, we added him
        for (String medicament : medicalsRecordDTO.getMedications()){
            List<Medicament> listMedicaments = medicamentRepository.findByName(medicament);

            Medicament medicamentObject = null;

            if(listMedicaments.isEmpty()){
                logger.info("This medicament isn't into BDD : {}",medicament);
                try{
                    medicamentObject = medicamentRepository.save(new Medicament(medicament));
                    logger.info("--- New medicament in BDD --- : {}", medicament);
                } catch(Exception e){
                    logger.error("Impossible to saved a new medicament in BDD : {}", e.getMessage());
                }
            } else {
                medicamentObject = listMedicaments.get(0);
            }

            medicalsRecord.getMedicaments().add(medicamentObject);
        }

        // Same for allergie
        for (String allergie : medicalsRecordDTO.getAllergies()){
            List<Allergie> listAllergies = allergieRepository.findByName(allergie);

            Allergie allergieObject = null;

            if(listAllergies.isEmpty()){
                logger.info("This allergie isn't into BDD : {}",allergie);
                try{
                    allergieObject = allergieRepository.save(new Allergie(allergie));
                    logger.info("--- New allergie in BDD --- : {}", allergie);
                } catch(Exception e){
                    logger.error("Impossible to saved a new medicament in BDD : {}", e.getMessage());
                }
            } else {
                allergieObject = listAllergies.get(0);
            }

            medicalsRecord.getAllergies().add(allergieObject);
        }
        try{
            medicalsRecordRepository.save(medicalsRecord);
            logger.info("--- New medicalsRecord --- : {}",medicalsRecord);
        } catch(Exception e){
            logger.error("Impossible to saved a new medicalsRecord : {} {}", medicalsRecord, e.getMessage());
        }
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }

    /**
     *
     * @param medicalsRecordDTO
     * @return {@link MedicalsRecordDTO}
     */
    @Override
    public MedicalsRecordDTO update(@RequestBody MedicalsRecordDTO medicalsRecordDTO){

        logger.trace("--- Call : update ---");
        logger.info("Data send by User : {}", medicalsRecordDTO);

        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
        MedicalsRecord medicalsRecord = new MedicalsRecord();

        // With the object Person, we found his Person.Id, and his MedicalsRecord.Id for the update
        if(!listPersons.isEmpty()){
            long idPerson = listPersons.get(0).getId();
            logger.info("Id of the person who has the medicalsRecords {}",idPerson);
            Optional<Long> idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);
            if(idMedicalsRecord.isPresent()){
                logger.info("Id of the medicalsRecords {}",idMedicalsRecord);
                medicalsRecord.setId(idMedicalsRecord.get());
                medicalsRecord.setPerson(listPersons.get(0));

                // For the medications indicated in the DTO, for each medicament, we check it if exist in BDD, else, we added him
                for (String medicament : medicalsRecordDTO.getMedications()){
                    List<Medicament> listMedicaments = medicamentRepository.findByName(medicament);

                    Medicament medicamentObject = null;

                    if(listMedicaments.isEmpty()){
                        logger.info("{} isn't into BDD", medicament);
                        try{
                            medicamentObject = medicamentRepository.save(new Medicament(medicament));
                            logger.info("--- New medicament in BDD --- : {}", medicamentObject.getName());
                        } catch(Exception e){
                            logger.error("Impossible to save a new medicament : {}", medicament);
                        }
                    } else {
                        medicamentObject = listMedicaments.get(0);
                    }

                    medicalsRecord.getMedicaments().add(medicamentObject);
                }

                // Same for allergie
                for (String allergie : medicalsRecordDTO.getAllergies()){
                    List<Allergie> listAllergies = allergieRepository.findByName(allergie);

                    Allergie allergieObject = null;

                    if(listAllergies.isEmpty()){
                        logger.info("{} isn't into BDD", allergie);
                        try{
                            allergieObject = allergieRepository.save(new Allergie(allergie));
                            logger.info("--- New allergie in BDD --- : {}", allergieObject.getName());
                        } catch(Exception e){
                            logger.error("Impossible to save a new allergie : {}", allergie);
                        }
                    } else {
                        allergieObject = listAllergies.get(0);
                    }

                    medicalsRecord.getAllergies().add(allergieObject);
                }
                try{
                    medicalsRecordRepository.save(medicalsRecord);
                    logger.info("--- New MedicalsRecord --- {}",medicalsRecord);
                } catch(Exception e){
                    logger.error("Impossible to save a new medicalsRecords : {}", e.getMessage());
                }
            }
        }
        logger.info("Values returned : {}", medicalsRecord);
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }

    /**
     *
     * @param medicalsRecordDTO
     * @return Reply message
     */
    @Override
    public String delete(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        logger.trace("--- Call : delete ---");
        logger.info("Data send by User : {}", medicalsRecordDTO);
        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());

        if(!listPersons.isEmpty()){
           long idPerson = listPersons.get(0).getId();
           logger.info("ID person found for delete this medicalsRecord : {}",idPerson);
           Optional<Long> idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);
           logger.info("IdMedicalsRecords found with this id Person : {}",idMedicalsRecord);
           try{
               medicalsRecordRepository.deleteById(idMedicalsRecord.get());
               logger.info("--- MedicalsRecord delete ---");
               return "Delete success";
           } catch(Exception e){
               logger.error("Impossible to delete this medicalsRecord : {}", e.getMessage());
               return "Delete error";
           }
        } else {
            logger.error("Nobody found with this pair of first/last name : {} {}", medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
            return "Delete error";
        }

    }

}


