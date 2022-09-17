package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.dto.MedicalsRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MedicalsRecordCRUD {
    @Autowired
    MedicalsRecordService medicalsRecordService;

    @PostMapping("/medicalRecord")
    public MedicalsRecordDTO addMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
       return medicalsRecordService.saveMedicalsRecord(medicalsRecordDTO);
    }

    @PutMapping("/medicalRecord")
    public MedicalsRecordDTO updateMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordService.updateMedicalsRecord(medicalsRecordDTO);
    }

    @DeleteMapping("/medicalRecord")
    public MedicalsRecordDTO deleteMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordService.deleteMedicalsRecord(medicalsRecordDTO);
    }

}
