package com.academy.terai.Controller;

import com.academy.terai.Model.Application;
import com.academy.terai.Service.ApplicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Application> getApplicationById(@PathVariable String id) throws NotFoundException {
//        return new ResponseEntity<>(applicationService.findById(id), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public Optional<Application> getApplicationById(@PathVariable String id) throws NotFoundException {
        return applicationService.findById(id);
   }

    @PostMapping
    ResponseEntity<Application> createApplication(@RequestBody Application application) throws NotFoundException {
        applicationService.addApplication(application);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Application> updateApplication(@RequestBody Application application, @PathVariable String id) throws NotFoundException {
        applicationService.updateApplication(application, id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteApplication(@PathVariable String id) throws NotFoundException {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
