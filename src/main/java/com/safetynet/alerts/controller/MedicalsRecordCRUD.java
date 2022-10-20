package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalsRecordCRUD {
    @Autowired
    MedicalsRecordService medicalsRecordService;

    @PostMapping("/medicalRecord")
    public MedicalsRecordDTO addMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
       return medicalsRecordService.save(medicalsRecordDTO);
    }

    @PutMapping("/medicalRecord")
    public MedicalsRecordDTO updateMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordService.update(medicalsRecordDTO);
    }

    @DeleteMapping("/medicalRecord")
    public String deleteMedicalsRecord(@RequestBody MedicalsRecordDTO medicalsRecordDTO){
        return medicalsRecordService.delete(medicalsRecordDTO);
    }

}

