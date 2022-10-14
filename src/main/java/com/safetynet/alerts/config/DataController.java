package com.safetynet.alerts.config;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class DataController {

    @Autowired
    ReadDataFile readDataFile;

    @PostMapping("/addData")
    public String addData() throws FileNotFoundException {
        return readDataFile.getDataContent();
    }

}