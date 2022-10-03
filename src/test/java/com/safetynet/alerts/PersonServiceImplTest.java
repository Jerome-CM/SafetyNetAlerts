package com.safetynet.alerts;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.List;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Autowired
    PersonRepository personRepository;

    private PersonDTO personDTO;
    private static PersonServiceImpl personServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Mock
    private ModelMapper modelMapper;

    @BeforeAll
    public static void setUpAll(){
        personServiceImpl = new PersonServiceImpl();
    }

    @BeforeEach
    public void setUp(){

        personDTO = new PersonDTO();

        personDTO.setLastName("Dupont");
        personDTO.setFirstName("Jean");
        personDTO.setAddress("2 Impasse Tournesol");
        personDTO.setCity("Blois");
        personDTO.setZip("41000");
        personDTO.setPhone("0633705593");
        personDTO.setEmail("j.dupont@safetynet.com");
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1991, 5,29);
        personDTO.setBirthdate(birthdate.getTime());

    }


    @Test
    public void getMailWithAddressTest()throws Exception{

        List<Person> listPerson = personRepository.getPersonWithAddress("644 Gershwin Cir");
        String result = listPerson.get(0).getEmail();
        assertEquals("", "jaboyd@email.com", result);

    }

   /* @Test
    public void addPersonInBDDTest() {


        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(personDTO);

        personServiceImpl.add(personDTO);

        List<Person> listPerson = personRepository.getPersons("Jean", "Dupont");
        String result = listPerson.get(0).getEmail();
        String expected = personDTO.getEmail();
        assertEquals("", expected, result);

    }*/

   /* @Test
    public void updatePersonInBDDTest() {

        personDTO.setAddress("2 Impasse Paquerette");
        personDTO.setCity("Vineuil");
        personDTO.setZip("41350");
        personDTO.setPhone("0633705594");
        personDTO.setEmail("j.dupont@safetynet.fr");

        personServiceImpl.update(personDTO);

        List<Person> listPerson = personRepository.getPersons("Jean", "Dupont");

        assertEquals("", personDTO.getAddress(), listPerson.get(0).getAddress());
        assertEquals("", personDTO.getCity(), listPerson.get(0).getCity());
        assertEquals("", personDTO.getZip(), listPerson.get(0).getZip());
        assertEquals("", personDTO.getPhone(), listPerson.get(0).getPhone());
        assertEquals("", personDTO.getEmail(), listPerson.get(0).getEmail());

    }*/

}
