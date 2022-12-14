package com.safetynet.alerts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class DataController {

    @Autowired
    InsertDataFromFile insertDataFromFIle;

    /**
     *
     * @param fileName name of the datafile in src/main/resources
     * @return Status message
     * @throws FileNotFoundException
     */
    @PostMapping("/addData")
    public String addData(@RequestParam String fileName) throws FileNotFoundException {
        return insertDataFromFIle.getDataContent(fileName);
    }
}