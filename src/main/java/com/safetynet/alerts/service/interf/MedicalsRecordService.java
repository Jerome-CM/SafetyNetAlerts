
package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.MedicalsRecordDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.repository.MedicalsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface MedicalsRecordService {

    public MedicalsRecordDTO save(MedicalsRecordDTO medicalsRecordDTO);

    public MedicalsRecordDTO update(MedicalsRecordDTO medicalsRecordDTO);

    public MedicalsRecordDTO delete(MedicalsRecordDTO medicalsRecordDTO);


}

