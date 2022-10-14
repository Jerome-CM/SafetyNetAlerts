package com.safetynet.alerts.HashCodeAndEquals;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class PersonHETest {

    @Autowired
    private ModelMapper modelMapper;

    public Person getPerson(){

        Person person = new Person();

        person.setLastName("Coffe");
        person.setFirstName("Jean-Pierre");
        person.setAddress("Rue de la soupe");
        person.setCity("Lanneray");
        person.setZip("28000");
        person.setPhone("0687451291");
        person.setEmail("jpc@manger.fr");

        return person;
    }

    public PersonDTO getPersonDTO(){

        Person person = getPerson();
        return modelMapper.map(person, PersonDTO.class);
    }



    @Test
    public void personIsEqualsTest(){

        Person person1 = getPerson();
        Person person2 = getPerson();

        assertEquals("", true, person1.equals(person2));
        assertTrue("", person1.hashCode() == person2.hashCode());

    }

    @Test
    public void personDTOIsEqualsTest(){

        PersonDTO person1 = getPersonDTO();
        PersonDTO person2 =  getPersonDTO();

        assertEquals("", true, person1.equals(person2));
        assertTrue("", person1.hashCode() == person2.hashCode());
    }

}
