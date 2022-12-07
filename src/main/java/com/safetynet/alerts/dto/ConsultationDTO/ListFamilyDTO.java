package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListFamilyDTO {

    private List<PersonInfoDTO> enfants = new ArrayList<>();
    private List<PersonInfoDTO> adultes = new ArrayList<>();

}
