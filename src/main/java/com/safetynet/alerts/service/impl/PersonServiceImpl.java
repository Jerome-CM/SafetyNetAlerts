package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public PersonDTO update(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }

    /*@Override
    public void deleteByFirstNameAndLastName(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        personRepository.delete(person.getFirstName(), person.getLastName());
    }*/
    // TODO Delete Person
    @Override
    public void delete(PersonDTO personDTO){
        Person person = modelMapper.map(personDTO, Person.class);
        System.out.println("First name : " + person.getFirstName());
        System.out.println("Last name : " + person.getLastName());
        personRepository.deletePerson(person.getFirstName(), person.getLastName());
    }

    @Override
    public ArrayList<String> getMailByCity(String city){

        ArrayList<String> listEmail = new ArrayList<String>();

        Iterable<Person> listPersons = personRepository.findByCity(city);
        listPersons.forEach(person -> listEmail.add(person.getEmail()));

        return listEmail;

    }
}
