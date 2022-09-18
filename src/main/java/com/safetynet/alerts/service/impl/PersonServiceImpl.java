package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public PersonDTO update(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public void delete(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        personRepository.delete(person);
    }

    @Override
    public Iterable<Person> getPersons(){
       return personRepository.findAll();
    }
}
