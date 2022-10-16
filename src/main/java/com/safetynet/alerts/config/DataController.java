package com.safetynet.alerts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class DataController {

    @Autowired
    ReadDataFile readDataFile;

    @PostMapping("/addData")
    public String addData(@RequestParam String fileName) throws FileNotFoundException {
        return readDataFile.getDataContent(fileName);
    }

}