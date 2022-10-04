package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

@Data
public class PersonAndMedicalsRecordDTO extends PersonInfoDTO {

    private ListMedicamentsAndAllergies medicalsRecord = new ListMedicamentsAndAllergies();
}
