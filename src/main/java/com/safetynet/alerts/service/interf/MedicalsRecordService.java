
package com.safetynet.alerts.service.interf;

import com.safetynet.alerts.dto.MedicalsRecordDTO;

public interface MedicalsRecordService {

    public MedicalsRecordDTO save(MedicalsRecordDTO medicalsRecordDTO);

    public MedicalsRecordDTO update(MedicalsRecordDTO medicalsRecordDTO);

    public String delete(MedicalsRecordDTO medicalsRecordDTO);

    public void truncateMedicament();

    public void truncateAllergie();

    public void truncateMedicalsRecord();

    public void truncateMedicalsRecordHasMedicament();

    public void truncateMedicalsRecordHasAllergie();
}

