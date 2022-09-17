package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PersonCRUD {
    @Autowired
    PersonsService personsService;

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO){
       return personsService.savePerson(personDTO);
    }

    @PutMapping("person")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO){
        return personsService.updatePerson(personDTO);
    }

    @DeleteMapping("person")
    public PersonDTO deletePerson(@RequestBody PersonDTO personDTO){
        return personsService.deletePerson(personDTO);
    }

}
