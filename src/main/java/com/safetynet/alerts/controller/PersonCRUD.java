package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PersonCRUD {
    @Autowired
    PersonServiceImpl personServiceImpl;

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO){
       return personServiceImpl.add(personDTO);
    }

    @PutMapping("/person")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO){
        return personServiceImpl.update(personDTO);
    }

    @DeleteMapping("/person")
    public void deletePerson(@RequestBody PersonDTO personDTO){
        personServiceImpl.delete(personDTO);

    }

    @GetMapping("/communityEmail")
    public ArrayList<String> getMailByCity(@RequestParam String city){
        return personServiceImpl.getMailByCity(city);
    }

}
