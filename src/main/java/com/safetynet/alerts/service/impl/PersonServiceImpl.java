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
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Person> getPersons(){
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public PersonDTO add(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        person = personRepository.save(person);
        logger.info("--- New user --- ID : " + person.getId());
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        List<Person>personfind = personRepository.getPersons(person.getFirstName(), person.getLastName());
        if(!personfind.isEmpty()) {
            person.setId(personfind.get(0).getId());
            personRepository.save(person);
            logger.info("--- User update --- ID : " + person.getId());
        } else {
            logger.error("Firstname : {} Lastname : {} is not find in BDD for update", person.getFirstName(), person.getLastName());
        }
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public String delete(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        List<Person> listPerson = personRepository.getPersons(person.getFirstName(), person.getLastName());
        if(!listPerson.isEmpty()){
            personRepository.delete(listPerson.get(0));
            logger.info("--- User delete ---");
            return "Succes";
        }
        logger.error("Cannot delete this user; ID :" + listPerson.get(0).getId());
        return "Error";
    }

}
