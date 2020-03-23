package com.academy.terai.Controller;

import com.academy.terai.Model.Status;
import com.academy.terai.Service.StatusService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/{id}")
//    public ResponseEntity<Status> getStatusById(@PathVariable String id) throws NotFoundException {
//        return new ResponseEntity<>(statusnService.findById(id), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public Optional<Status> getStatusById(@PathVariable String id) throws NotFoundException {
        return statusnService.findById(id);
    }

    @PostMapping
    ResponseEntity<Status> createStatus(@RequestBody Status status) throws NotFoundException {
        statusnService.addStatus(status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Status> updateStatus(@RequestBody Status status, @PathVariable String id) throws NotFoundException {
        statusnService.updateStatus(status, id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteStatus(@PathVariable String id) throws NotFoundException {
        statusnService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
