package com.safetynet.alerts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MedicalsRecordDTO {

    private String lastName;
    private String firstName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthdate;
    private List<String> medicaments;
    private List<String> allergies;

}
