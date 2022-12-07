package com.safetynet.alerts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
public class PersonDTO {

    private String lastName;
    private String firstName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy") @Nullable
    private Date birthdate;

}
