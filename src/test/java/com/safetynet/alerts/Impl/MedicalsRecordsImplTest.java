package com.safetynet.alerts.Impl;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MedicalsRecordsImplTest {

    @Autowired
    MedicalsRecordService medicalsRecordService;

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    private MedicalsRecordDTO medicalsRecordDTO;

    @BeforeEach
    public void setUpEach(){

        medicalsRecordDTO  = new MedicalsRecordDTO();

        medicalsRecordDTO.setFirstName("Jean-Pierre");
        medicalsRecordDTO.setLastName("Coffe");
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1938,9,29);
        medicalsRecordDTO.setBirthdate(birthdate.getTime());
        List<String> medicaments = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        medicaments.add("Ibuprofene : 400mg");
        medicaments.add("Vitamine C : 1000mg");
        allergies.add("Kiwi");
        allergies.add("Gluten");
        medicalsRecordDTO.setMedications(medicaments);
        medicalsRecordDTO.setAllergies(allergies);
    }

    @Test
    public void saveMedicalsRecordTest(){

        Optional<Long> idOpt = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(personRepository.getPersons("Jean-Pierre", "Coffe").get(0).getId());

        if(idOpt.isEmpty()){
            medicalsRecordService.save(medicalsRecordDTO);
        }

        Optional<Long> idOptResult = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(personRepository.getPersons("Jean-Pierre", "Coffe").get(0).getId());

        assertTrue(idOptResult.isPresent());

    }

    @Test
    public void saveMedicalsRecordWithPersonDoesNotExistTest(){

        medicalsRecordDTO.setFirstName("Pierre-Jean");
        medicalsRecordDTO.setLastName("Tounois");

        medicalsRecordService.save(medicalsRecordDTO);

        Optional<Long> idOptResult = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(personRepository.getPersons("Pierre-Jean", "Tounois").get(0).getId());

        assertTrue(idOptResult.isPresent());

    }

    @Test
    public void UpdateMedicalsRecordsTest(){

        this.saveMedicalsRecordTest();

        List<String> medicaments = medicalsRecordDTO.getMedications();
        List<String> allergies = medicalsRecordDTO.getAllergies();
        medicaments.add("amoxicilline : 1000mg");
        allergies.remove("Kiwi");

        medicalsRecordDTO.setMedications(medicaments);
        medicalsRecordDTO.setAllergies(allergies);

        medicalsRecordService.update(medicalsRecordDTO);

        Optional<Long> idOpt = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(personRepository.getPersons("Jean-Pierre", "Coffe").get(0).getId());
        List<String> medicamentsNew = medicalsRecordRepository.findMedicamentsListByMedicalsRecordsId(idOpt.get());
        List<String> allergiesNew = medicalsRecordRepository.findAllergiesListByMedicalsRecordsId(idOpt.get());

        int nbrMedicaments = medicamentsNew.size();
        int nbrAllergie = allergiesNew.size();

        assertEquals(3, nbrMedicaments);
        assertEquals(1, nbrAllergie);
    }

    @Test
    public void deleteMedicalsRecordsTest(){

        String messageReturn = medicalsRecordService.delete(medicalsRecordDTO);

        assertEquals("Delete success", messageReturn);

    }

    @Test
    public void deleteWithUnknowPersonTest(){

        medicalsRecordDTO.setFirstName("Pierre-Paul");
        medicalsRecordDTO.setLastName("Jacques");

        String messageReturn = medicalsRecordService.delete(medicalsRecordDTO);

        assertEquals("Delete error", messageReturn);

    }

}
