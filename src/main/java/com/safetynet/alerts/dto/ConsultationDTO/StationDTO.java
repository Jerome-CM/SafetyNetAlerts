package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

@Data
public class StationDTO extends ListMedicamentsAndAllergies{

    private String nom;
    private int age;
    private String phone;

}
