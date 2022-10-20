package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.service.interf.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonCRUD {
    @Autowired
    PersonService personService;

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO){
       return personService.add(personDTO);
    }

    @PutMapping("/person")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO){
        return personService.update(personDTO);
    }

    @DeleteMapping("/person")
    public String deletePerson(@RequestBody PersonDTO personDTO){
       return personService.delete(personDTO);
    }

}
