package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.service.impl.MedicalsRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalsRecordCRUD {
    @Autowired
    MedicalsRecordServiceImpl medicalsRecordServiceImpl;

    @PostMapping("/medicalRecord")
    public MedicalsRecordDTO addMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
       return medicalsRecordServiceImpl.save(medicalsRecordDTO);
    }

    @PutMapping("/medicalRecord")
    public MedicalsRecordDTO updateMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordServiceImpl.update(medicalsRecordDTO);
    }

    @DeleteMapping("/medicalRecord")
    public String deleteMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordServiceImpl.delete(medicalsRecordDTO);
    }

}

