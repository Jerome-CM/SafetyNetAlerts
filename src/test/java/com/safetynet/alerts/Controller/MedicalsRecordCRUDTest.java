package com.safetynet.alerts.Controller;

import com.safetynet.alerts.controller.MedicalsRecordCRUD;
import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.service.impl.MedicalsRecordServiceImpl;
import com.safetynet.alerts.utility.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MedicalsRecordCRUD.class)
public class MedicalsRecordCRUDTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static MedicalsRecordServiceImpl medicalsRecordServiceImpl;

    public MedicalsRecordDTO loadMedicalsRecordDTO(){
        MedicalsRecordDTO medicalsRecordDTO = new MedicalsRecordDTO();

        medicalsRecordDTO.setLastName("Coffe");
        medicalsRecordDTO.setFirstName("Jean-Pierre");
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1938,9,29);
        medicalsRecordDTO.setBirthdate(birthdate.getTime());
        List<String> medicaments = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        medicaments.add("Ibuprofene : 400mg");
        allergies.add("Gluten");
        medicalsRecordDTO.setMedications(medicaments);
        medicalsRecordDTO.setAllergies(allergies);

        return medicalsRecordDTO;
    }

    @Test
    public void endpointAddMedicalsRecordTest() throws Exception {

        MedicalsRecordDTO medicalsRecordDTO = loadMedicalsRecordDTO();

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(medicalsRecordDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointUpdateMedicalsRecordTest() throws Exception {

        MedicalsRecordDTO medicalsRecordDTO = loadMedicalsRecordDTO();
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1938,9,30);
        medicalsRecordDTO.setBirthdate(birthdate.getTime());

        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(medicalsRecordDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    public void endpointDeleteMedicalsRecordTest() throws Exception {

        MedicalsRecordDTO medicalsRecordDTO = loadMedicalsRecordDTO();

        mockMvc.perform(delete("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.jsonEncode(medicalsRecordDTO)))
                        .andExpect(status().isOk());
    }
}