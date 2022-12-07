package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ListMedicamentsAndAllergies {

    List<String> medications = new ArrayList<>();
    List<String> allergies = new ArrayList<>();
}
