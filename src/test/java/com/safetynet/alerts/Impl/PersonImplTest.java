package com.safetynet.alerts.Impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@SpringBootTest
public class PersonImplTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    private PersonDTO personDTO;

    @BeforeEach
    public void setUpEach(){
        personDTO  = new PersonDTO();
    }

    @Test
    public void savePersonTest(){

        List<Person> listTest = personRepository.getPersons("Jean-Pierre", "Coffe");

        personDTO.setLastName("Coffe");
        personDTO.setFirstName("Jean-Pierre");
        personDTO.setAddress("Rue de la soupe");
        personDTO.setCity("Lanneray");
        personDTO.setZip("28000");
        personDTO.setPhone("0687451291");
        personDTO.setEmail("jpc@manger.fr");

        personService.add(personDTO);

        List<Person> listTestNew = personRepository.getPersons("Jean-Pierre", "Coffe");
        assertEquals("", listTest.size() + 1, listTestNew.size());

    }

    @Test
    public Person getPersonTest(){
        this.savePersonTest();
        List<Person> listTest = personRepository.getPersons("Jean-Pierre", "Coffe");

        assertEquals("", "Jean-Pierre", listTest.get(0).getFirstName());
        return listTest.isEmpty()?null:listTest.get(0);
    }


    @Test
    public void updatePersonTest(){

        Person person = getPersonTest();

        if(person!=null){

            PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

            Calendar birthdate = Calendar.getInstance();
            birthdate.set(1938,9,29);
            personDTO.setBirthdate(birthdate.getTime());

            personDTO = personService.update(personDTO);
            long birthTime = personDTO.getBirthdate()==null?0:personDTO.getBirthdate().getTime();

            assertEquals("", birthdate.getTime().getTime(), birthTime);

        } else {
            assertFalse("",true);
        }
    }

    @Test
    public void failUpdateExceptionTest(){

        Person person = getPersonTest();

        if(person!=null){
            person.setFirstName("Pierre-Paul");
            person.setLastName("Jacques");
            PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

            personService.update(personDTO);

            List<Person> listTest = personRepository.getPersons("Pierre-Paul", "Jacques");
            assertEquals("", 0, listTest.size());

        }

    }

    @Test
    public void deletePersonTest(){
        this.savePersonTest();

        personDTO.setLastName("Coffe");
        personDTO.setFirstName("Jean-Pierre");

        String messageReturn = personService.delete(personDTO);

        assertEquals("", "User delete", messageReturn);

    }
}