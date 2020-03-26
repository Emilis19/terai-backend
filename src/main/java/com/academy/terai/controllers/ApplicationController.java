package com.academy.terai.controllers;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Application;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.service.ApplicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationHrResponse>> getAllApplications() {
        List<ApplicationHrResponse> applications = applicationService.findAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable String id) throws ApiRequestException {
        Application returnApplication = applicationService.findById(id);
        return new ResponseEntity<>(returnApplication, HttpStatus.OK);
   }

    @PostMapping
    ResponseEntity<HttpStatus> createApplication(@RequestBody Application application) throws ApiRequestException {
        applicationService.addApplication(application);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> updateApplication(@RequestBody Application application, @PathVariable String id) throws ApiRequestException {
        applicationService.updateApplication(application, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteApplication(@PathVariable String id) throws ApiRequestException {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
