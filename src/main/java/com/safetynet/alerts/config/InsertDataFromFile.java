package com.safetynet.alerts.config;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.service.interf.FirestationService;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import com.safetynet.alerts.service.interf.PersonService;
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
public class InsertDataFromFile {
    private static final Logger logger = LogManager.getLogger(InsertDataFromFile.class);

    @Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalsRecordService medicalsRecordService;

    @Autowired
    TruncateTable truncateTable;

    @Autowired
    ModelMapper modelMapper;

    /**
     *
     * @param fileName fileName name of the datafile with JSON extension in src/main/resources
     * @return status message
     * @throws FileNotFoundException
     */
    public String getDataContent(String fileName) throws FileNotFoundException {

        try{
            truncateTable.truncateAllTables();
            logger.info("--- DATABASE TRUNCATED ---");
        } catch(Exception e){
            logger.error("Impossible to truncate the database, {}", e.getMessage());
        }

        JSONParser jsonP = new JSONParser();
        try {
            // Try to get a Json Data
            JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader("src/main/resources/" + fileName));

            // Explode different keys
            JSONArray persons = (JSONArray) jsonO.get("persons");
            JSONArray firestations = (JSONArray) jsonO.get("firestations");
            JSONArray medicalsRecords = (JSONArray) jsonO.get("medicalrecords");

            for(int p = 0 ; p < persons.size() ; p++){
                Person person = new Person();
                MedicalsRecordDTO medicalsRecordDTO = new MedicalsRecordDTO();

                JSONObject data = (JSONObject) persons.get(p);
                JSONObject dataMR = (JSONObject) medicalsRecords.get(p);

                // Set Person with Json information
                person.setFirstName((String)data.get("firstName"));
                person.setLastName((String)data.get("lastName"));
                person.setAddress((String)data.get("address"));
                person.setCity((String)data.get("city"));
                person.setZip((String)data.get("zip"));
                person.setPhone((String)data.get("phone"));
                person.setEmail((String)data.get("email"));

                // Refactorize Date Json: String MM/dd/yyyy to Date dd/MM/yyyy
                String input = (String)dataMR.get("birthdate");
                String[] array = input.split("/");
                int day = Integer.parseInt(array[1]);
                int month = Integer.parseInt(array[0])-1;
                int year = Integer.parseInt(array[2]);
                Calendar birthdateRefactor = Calendar.getInstance();
                birthdateRefactor.set(year,month,day);

                person.setBirthdate(birthdateRefactor.getTime());

                //Set MedicalsRecord with Json information
                medicalsRecordDTO.setFirstName((String)dataMR.get("firstName"));
                medicalsRecordDTO.setLastName((String)dataMR.get("lastName"));
                medicalsRecordDTO.setBirthdate(person.getBirthdate());
                medicalsRecordDTO.setMedications((List<String>) dataMR.get("medications"));
                medicalsRecordDTO.setAllergies((List<String>) dataMR.get("allergies"));

                PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

                // Save data in BDD
                try{
                    personService.add(personDTO);
                    medicalsRecordService.save(medicalsRecordDTO);
                } catch(Exception e){
                    logger.error("{}", e.getMessage());
                    return "Error : Data not injected in BDD";
                }
            }

            // Same for Firestation
            for(int f = 0 ; f < firestations.size() ; f++){
                Firestation firestation = new Firestation();
                JSONObject data = (JSONObject) firestations.get(f);

                firestation.setAddress((String)data.get("address"));
                firestation.setStation((String)data.get("station"));

                FirestationDTO firestationDTO = new FirestationDTO();

                firestationDTO = modelMapper.map(firestation, FirestationDTO.class);

                try{
                    firestationService.saveFirestation(firestationDTO);
                } catch(Exception e){
                    logger.error("{}", e.getMessage());
                    return "Error : Data not injected in BDD";
                }
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return "File read, all data injected";
    }
}