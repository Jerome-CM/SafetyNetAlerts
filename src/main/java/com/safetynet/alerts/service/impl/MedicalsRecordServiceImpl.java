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
        medicalsRecordRepository.save(medicalsRecord);
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }


// TODO to control
    public MedicalsRecordDTO update(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        MedicalsRecord medicalsRecord = modelMapper.map(medicalsRecordDTO, MedicalsRecord.class);

        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());

        if(!listPersons.isEmpty()){
            long idPerson = listPersons.get(0).getId();
            long idMedicalsRecord;
            idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson).get(0);
            if(idMedicalsRecord != 0){
                medicalsRecordRepository.save(medicalsRecord);
            }
        }
        return modelMapper.map(medicalsRecord, MedicalsRecordDTO.class);
    }

    public boolean delete(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        List<Person> listPersons = personRepository.getPersons(medicalsRecordDTO.getFirstName(), medicalsRecordDTO.getLastName());

        if(!listPersons.isEmpty()){
           long idPerson = listPersons.get(0).getId();
           long idMedicalsRecord = medicalsRecordRepository.findMedicalsRecordsIdByIdPerson(idPerson).get(0);
           medicalsRecordRepository.deleteById(idMedicalsRecord);
            return true;
        } else {
            return false;
        }

    }

}


