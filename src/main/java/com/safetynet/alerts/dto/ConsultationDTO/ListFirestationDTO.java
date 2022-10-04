package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListFirestationDTO {

    private List<PersonInfoDTO> Habitants = new ArrayList<>();
    private int nbrAdults = 0;
    private int nbrEnfants = 0;

}
