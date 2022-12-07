package com.safetynet.alerts.config;

import com.safetynet.alerts.service.interf.FirestationService;
import com.safetynet.alerts.service.interf.MedicalsRecordService;
import com.safetynet.alerts.service.interf.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruncateTable {

    @Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalsRecordService medicalsRecordService;

    public void truncateAllTables(){

        medicalsRecordService.truncateMedicalsRecordHasAllergie();
        medicalsRecordService.truncateMedicalsRecordHasMedicament();
        medicalsRecordService.truncateMedicalsRecord();
        medicalsRecordService.truncateMedicament();
        medicalsRecordService.truncateAllergie();
        firestationService.truncateFirestation();
        personService.truncatePerson();
    }
}
