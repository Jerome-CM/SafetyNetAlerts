
package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.MedicalsRecordDTO;

public interface MedicalsRecordService {

    public MedicalsRecordDTO save(MedicalsRecordDTO medicalsRecordDTO);

    public MedicalsRecordDTO update(MedicalsRecordDTO medicalsRecordDTO);

    public String delete(MedicalsRecordDTO medicalsRecordDTO);


}

