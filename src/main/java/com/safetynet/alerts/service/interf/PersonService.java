package com.safetynet.alerts.service.interf;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;


public interface PersonService {

	public PersonDTO add(PersonDTO personDTO);

	public PersonDTO update(PersonDTO personDTO);

	public String delete(PersonDTO personDTO);

	public void truncatePerson();
}
 