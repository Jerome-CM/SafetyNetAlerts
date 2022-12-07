package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PersonDTO add(PersonDTO personDTO) {
        logger.trace("--- Call : add ( save a new person ) ---");
        logger.info("Data send by User : {}", personDTO);

        Person person = modelMapper.map(personDTO, Person.class);

        person = personRepository.save(person);
        logger.info("--- New user --- ID : {}, Firstname : {}, Lastname : {}", person.getId(), person.getFirstName(), person.getLastName());
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO){

        logger.trace("--- Call : update ---");
        logger.info("Data send by User : {}", personDTO);

        Person person = modelMapper.map(personDTO, Person.class);
        List<Person> personfind = personRepository.getPersons(person.getFirstName(), person.getLastName());

        if(!personfind.isEmpty()) {

            logger.info("Olds infos Person : {}", personfind.get(0));

            person.setId(personfind.get(0).getId());
            try{
                personRepository.save(person);
                logger.info("--- User update --- ");
                logger.info("News infos Person : {}", person);
            } catch(Exception e){
                logger.error("Error : Can't update person : {}", e.getMessage());
            }
        } else {
            logger.error("Firstname : {} Lastname : {} is not find in BDD for update", person.getFirstName(), person.getLastName());
        }
        return modelMapper.map(person, PersonDTO.class);
    }

    /**
     *
     * @param personDTO
     * @return Reply message
     */
    @Override
    public String delete(PersonDTO personDTO){

        logger.trace("--- Call : delete ---");
        logger.info("Data send by User : {}", personDTO);

        Person person = modelMapper.map(personDTO, Person.class);
        List<Person> listPerson = personRepository.getPersons(person.getFirstName(), person.getLastName());
        if(!listPerson.isEmpty()){
            try{
                personRepository.delete(listPerson.get(0));
                logger.info("--- User delete ---");
                return "User delete";
            } catch(Exception e){
                logger.error("Impossible to delete this user : {}", e.getMessage());
                return "Error : user isn't deleted";
            }

        } else {
            logger.error("Can't find this person with firstname : {} and lastname : {}", person.getFirstName(), person.getLastName());
            return "Error : user isn't deleted";
        }
    }

    @Override
    public void truncatePerson(){
        personRepository.truncatePerson();
    }

}
