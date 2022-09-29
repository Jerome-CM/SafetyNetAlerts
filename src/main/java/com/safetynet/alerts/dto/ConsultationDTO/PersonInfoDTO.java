package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

@Data
public class PersonInfoDTO extends ListMedicamentsAndAllergies{

    private String nom;
    private String address;
    private int age;
    private String mail;
}
