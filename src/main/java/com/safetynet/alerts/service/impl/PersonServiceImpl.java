package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.PersonService;
import com.safetynet.alerts.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDTO add(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }
// TODO A vérifier
    @Override
    public PersonDTO update(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        List<Person>personfind = personRepository.getPersons(person.getFirstName(), person.getLastName());
        if(!personfind.isEmpty()) {
            personRepository.save(person);
        }
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public void delete(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        List<Person> listPerson = personRepository.getPersons(person.getFirstName(), person.getLastName());
        if(!listPerson.isEmpty()){
            personRepository.delete(listPerson.get(0));
        }
    }

}
