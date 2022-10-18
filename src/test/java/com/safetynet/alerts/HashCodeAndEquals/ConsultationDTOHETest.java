package com.safetynet.alerts.HashCodeAndEquals;

import com.safetynet.alerts.dto.ConsultationDTO.*;
import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class ConsultationDTOHETest {

    public PersonInfoDTO getPersonInfoDTO(){

        PersonInfoDTO person = new PersonInfoDTO();

        person.setLastName("Coffe");
        person.setFirstName("Jean-Pierre");
        person.setAddress("Rue de la soupe");
        person.setAge(30);
        person.setPhone("0687451291");
        person.setMail("jpc@manger.fr");

        return person;
    }

    public ListFirestationDTO getListFireDTO(){

        ListFirestationDTO listFire = new ListFirestationDTO();

        listFire.setNbrAdults(9);
        listFire.setNbrEnfants(2);
        List<PersonInfoDTO> listHab = new ArrayList<>();
        listHab.add(getPersonInfoDTO());
        listHab.add(getPersonInfoDTO());
        listFire.setHabitants(listHab);

        return listFire;
    }


    public ListMedicamentsAndAllergies getListMedicamentsAndAllergies(){

        ListMedicamentsAndAllergies listMedicamentsAndAllergies = new ListMedicamentsAndAllergies();

        List<String> medicaments = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        medicaments.add("Doliprane");
        allergies.add("Pollen");

        listMedicamentsAndAllergies.setMedications(medicaments);
        listMedicamentsAndAllergies.setAllergies(allergies);

        return listMedicamentsAndAllergies;

    }

    public ListFamillyDTO getFamilleDTO(){

        ListFamillyDTO listFamille = new ListFamillyDTO();

        List<PersonInfoDTO> enfants = new ArrayList<>();
        List<PersonInfoDTO> adultes = new ArrayList<>();
        enfants.add(getPersonInfoDTO());
        adultes.add(getPersonInfoDTO());

        return listFamille;
    }


    @Test
    public void PersonInfoDTOIsEqualsTest(){

        PersonInfoDTO person1 = getPersonInfoDTO();
        PersonInfoDTO person2 = getPersonInfoDTO();

        assertEquals("", true, person1.equals(person2));
        assertTrue("", person1.hashCode() == person2.hashCode());

    }

    @Test
    public void listFamillyDTOTest(){

        ListFamillyDTO listFamille = getFamilleDTO();

        assertEquals("", true, listFamille.getEnfants().equals(listFamille.getAdultes()));
        assertTrue("", listFamille.getEnfants().hashCode() == listFamille.getAdultes().hashCode());
    }


    @Test
    public void listMedicamentsAndAllergiesTest(){

        ListMedicamentsAndAllergies list1 = getListMedicamentsAndAllergies();
        ListMedicamentsAndAllergies list2 = getListMedicamentsAndAllergies();

        assertEquals("", true, list1.equals(list2));
        assertTrue("", list1.hashCode() == list2.hashCode());

    }

    @Test
    public void listFireDTOTest(){

        ListFirestationDTO list1 = getListFireDTO();
        ListFirestationDTO list2 = getListFireDTO();

        assertEquals("", true, list1.equals(list2));
        assertTrue("", list1.hashCode() == list2.hashCode());

    }


}

