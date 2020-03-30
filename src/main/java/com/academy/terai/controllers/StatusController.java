package com.academy.terai.controllers;

import com.academy.terai.model.ApplicationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/status")
public class StatusController {


    @GetMapping
    public ResponseEntity<List<String>> getAllStatuses() {
        List<String> statuses = new ArrayList();
        for (ApplicationStatus stat : ApplicationStatus.values()){
            statuses.add(stat.toString());
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }


}
