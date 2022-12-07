package com.safetynet.alerts.Controller;

import com.safetynet.alerts.controller.PersonCRUD;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import com.safetynet.alerts.utility.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonCRUD.class)
public class PersonCRUDTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static PersonServiceImpl personServiceImpl;

    public PersonDTO loadPersonDTO(){
        PersonDTO personDTO = new PersonDTO();

        personDTO.setLastName("Coffe");
        personDTO.setFirstName("Jean-Pierre");
        personDTO.setAddress("Rue de la soupe");
        personDTO.setCity("Lanneray");
        personDTO.setZip("28000");
        personDTO.setPhone("0687451291");
        personDTO.setEmail("jpc@manger.fr");

        return personDTO;
    }

    @Test
    public void endpointAddPersonTest() throws Exception {

        PersonDTO personDTO = loadPersonDTO();

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(personDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointUpdatePersonTest() throws Exception {

        PersonDTO personDTO = loadPersonDTO();

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(personDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointDeletePersonTest() throws Exception {

        PersonDTO personDTO = loadPersonDTO();

        mockMvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(personDTO)))
                        .andExpect(status().isOk());
    }
}