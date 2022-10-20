package com.safetynet.alerts.HashCodeAndEquals;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.model.Allergie;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Medicament;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.AllergieRepository;
import com.safetynet.alerts.repository.MedicamentRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class MedicalsRecordHETest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    AllergieRepository allergieRepository;

    public MedicalsRecord getMR(){
        MedicalsRecord medicalsRecord  = new MedicalsRecord();

        Person person = new Person();

        person.setLastName("Coffe");
        person.setFirstName("Jean-Pierre");
        person.setAddress("Rue de la soupe");
        person.setCity("Lanneray");
        person.setZip("28000");
        person.setPhone("0687451291");
        person.setEmail("jpc@manger.fr");

        medicalsRecord.setPerson(person);
        List<Medicament> medicaments = (List<Medicament>) medicamentRepository.findAll();
        List<Allergie> allergies = (List<Allergie>) allergieRepository.findAll();
        medicalsRecord.setMedicaments(medicaments);
        medicalsRecord.setAllergies(allergies);

        return medicalsRecord;
    }

    public MedicalsRecordDTO getMRDTO(){

        MedicalsRecordDTO medicalsRecordDTO  = new MedicalsRecordDTO();

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

        return medicalsRecordDTO;
    }


    @Test
    public void medicalsRecordIsEqualsTest(){

        MedicalsRecord person1 = getMR();
        MedicalsRecord person2 = getMR();

        assertEquals("", true, person1.equals(person2));
        assertTrue("", person1.hashCode() == person2.hashCode());

    }

    @Test
    public void medicalsRecordDTOIsEqualsTest(){

        MedicalsRecordDTO person1 = getMRDTO();
        MedicalsRecordDTO person2 = getMRDTO();

        assertEquals("", true, person1.equals(person2));
        assertTrue("", person1.hashCode() == person2.hashCode());
    }

}
