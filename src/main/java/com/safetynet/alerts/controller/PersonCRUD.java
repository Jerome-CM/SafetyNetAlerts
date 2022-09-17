package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonCRUD {
    @Autowired
    PersonService personsService;

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
