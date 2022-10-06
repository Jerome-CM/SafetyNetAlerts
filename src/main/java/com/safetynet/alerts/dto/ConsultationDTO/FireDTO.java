package com.safetynet.alerts.dto.ConsultationDTO;

import com.safetynet.alerts.dto.FirestationDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FireDTO {

    List<PersonAndMedicalsRecordDTO> person = new ArrayList<>();
    private FirestationDTO firestation;

}
