package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FireDTO extends ListMedicamentsAndAllergies{

    private String nom;
    private String phone;
    private int age;
    private String firestationNumber;

}
