package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.model.MedicalsRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import com.safetynet.alerts.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalsRecordServiceImpl implements MedicalsRecordService {

    @Autowired
    MedicalsRecordRepository medicalsRecordRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;


    public MedicalsRecordDTO save(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        MedicalsRecord medicalsRecord = modelMapper.map(medicalsRecordDTO, MedicalsRecord.class);

        // Si l'on connait la personne on agit sinon on ne fait rien
        // on créer la paire idMedicalRecords <-> idPerson
           // SAVE(??)
        // On ajout la date de naissance à Person
        // on regarde s'il y a des allergies,
        // on ajout à la BDD l'allergie si elle est inconnue, sinon on l'ajoute à la table de jointure
        // pareil pour les médicaments
        medicalsRecordRepository.save(medicalsRecord);
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }


// TODO to control
    public MedicalsRecordDTO update(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        MedicalsRecord medicalsRecord = modelMapper.map(medicalsRecordDTO, MedicalsRecord.class);

        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());

        if(!listPersons.isEmpty()){
            long idPerson = listPersons.get(0).getId();
            Optional<Long> idMedicalsRecord;
            idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);
            if(idMedicalsRecord.isPresent()){
                medicalsRecordRepository.save(medicalsRecord);
            }
        }
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }

    public boolean delete(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());
        if(!listPersons.isEmpty()){
           long idPerson = listPersons.get(0).getId();
           Optional<Long> idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson);
           medicalsRecordRepository.deleteById(idMedicalsRecord.get());
            return true;
        } else {
            return false;
        }

    }

}


