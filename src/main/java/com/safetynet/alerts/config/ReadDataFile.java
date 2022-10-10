package com.safetynet.alerts.config;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

@Service
public class ReadDataFile {
    private static final Logger logger = LogManager.getLogger(ReadDataFile.class);

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    ModelMapper modelMapper;

    public void getDataContent() throws FileNotFoundException {

        JSONParser jsonP = new JSONParser();
        try {
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("src/main/resources/data.json"));

            JSONArray persons = (JSONArray) jsonO.get("persons");
            JSONArray firestation = (JSONArray) jsonO.get("firestations");
            JSONArray medicalsRecord = (JSONArray) jsonO.get("medicalrecords");

            for(int i = 0 ; i < persons.size() ; i++){
                Person person = new Person();

                JSONObject data = (JSONObject) persons.get(i);
                JSONObject dataMR = (JSONObject) medicalsRecord.get(i);

                person.setFirstName((String)data.get("lastName"));
                person.setLastName((String)data.get("firstName"));
                person.setAddress((String)data.get("address"));
                person.setCity((String)data.get("city"));
                person.setZip((String)data.get("zip"));
                person.setPhone((String)data.get("phone"));
                person.setEmail((String)data.get("email"));

                // Refactorisation de la date Json: String MM/dd/yyyy vers Date dd/MM/yyyy
                String input = (String)dataMR.get("birthdate");
                String[] array = input.split("/");
                int day = Integer.parseInt(array[1]);
                int month = Integer.parseInt(array[0])-1;
                int year = Integer.parseInt(array[2]);
                Calendar birthdateRefactor = Calendar.getInstance();
                birthdateRefactor.set(year,month,day);

                person.setBirthdate(birthdateRefactor.getTime());

                PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

                try{
                    personService.add(personDTO);
                } catch(Exception e){
                    logger.error("{}", e.getMessage());
                }

            }

        } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
        }

    }
}