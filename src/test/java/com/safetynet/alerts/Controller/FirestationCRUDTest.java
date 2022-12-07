package com.safetynet.alerts.Controller;

import com.safetynet.alerts.controller.FirestationCRUD;
import com.safetynet.alerts.controller.PersonCRUD;
import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.service.impl.FirestationServiceImpl;
import com.safetynet.alerts.utility.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FirestationCRUD.class)
public class FirestationCRUDTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static FirestationServiceImpl firestationServiceImpl;

    public FirestationDTO loadFirestationDTO(){
        FirestationDTO firestationDTO = new FirestationDTO();

        firestationDTO.setStation("99");
        firestationDTO.setAddress("Rue de la soupe");

        return firestationDTO;
    }

    @Test
    public void endpointAddFirestationTest() throws Exception {

        FirestationDTO firestationDTO = loadFirestationDTO();

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(firestationDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointUpdateFirestationTest() throws Exception {

        FirestationDTO firestationDTO = loadFirestationDTO();
        firestationDTO.setStation("88");

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(firestationDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointDeleteFirestationTest() throws Exception {

        FirestationDTO firestationDTO = loadFirestationDTO();

        mockMvc.perform(delete("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(firestationDTO)))
                        .andExpect(status().isOk());
    }
}