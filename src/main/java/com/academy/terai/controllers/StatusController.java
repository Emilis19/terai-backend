package com.academy.terai.controllers;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Status;
import com.academy.terai.service.StatusService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusnService;

    @Autowired
    public StatusController(StatusService statusnService) {
        this.statusnService = statusnService;
    }


    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = statusnService.findAll();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable String id) throws ApiRequestException {
        return statusnService.findById(id);
    }

    @PostMapping
    ResponseEntity<HttpStatus> createStatus(@RequestBody Status status) throws ApiRequestException {
        statusnService.addStatus(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> updateStatus(@RequestBody Status status, @PathVariable String id) throws ApiRequestException {
        statusnService.updateStatus(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteStatus(@PathVariable String id) throws ApiRequestException {
        statusnService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
