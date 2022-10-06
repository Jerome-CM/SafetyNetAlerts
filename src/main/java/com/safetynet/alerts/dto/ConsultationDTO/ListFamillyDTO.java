package com.safetynet.alerts.dto.ConsultationDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListFamillyDTO {

    private List<PersonInfoDTO> enfants = new ArrayList<>();
    private List<PersonInfoDTO> adultes = new ArrayList<>();

}
