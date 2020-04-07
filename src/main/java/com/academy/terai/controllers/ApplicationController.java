package com.academy.terai.controllers;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Application;
import com.academy.terai.model.Comment;
import com.academy.terai.model.request.ApplicationRequest;
import com.academy.terai.model.request.ApplicationUpdateRequest;
import com.academy.terai.model.request.CommentRequest;
import com.academy.terai.model.request.StatusChangeReqeust;
import com.academy.terai.model.response.ApplicationFullResponse;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.service.ApplicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/applications")
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
    public ResponseEntity<ApplicationFullResponse> getApplicationById(@PathVariable String id) throws ApiRequestException {
        ApplicationFullResponse returnApplication = applicationService.findById(id);
        return new ResponseEntity<>(returnApplication, HttpStatus.OK);
   }

    @PostMapping
    ResponseEntity<HttpStatus> createApplication(@RequestBody ApplicationRequest application) throws ApiRequestException {
        applicationService.addApplication(application);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> updateApplication(@RequestBody ApplicationUpdateRequest application, @PathVariable String id) throws ApiRequestException {
        applicationService.updateApplication(application, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteApplication(@PathVariable String id) throws ApiRequestException {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/changestatus")
    ResponseEntity<HttpStatus> updateStatus(@RequestBody StatusChangeReqeust reqeust) throws ApiRequestException {
        applicationService.changeStatus(reqeust);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/addcomment")
    ResponseEntity<HttpStatus> addComment(@RequestBody CommentRequest request) throws ApiRequestException {
        applicationService.addComment(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("getcomments/{id}")
    ResponseEntity<List<Comment>> getCommentsById(@PathVariable String id) throws ApiRequestException {
        List<Comment> returnList = applicationService.getComments(id);
        return new ResponseEntity<List<Comment>>(returnList, HttpStatus.OK);
    }
}
