package com.academy.terai.controllers;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.request.DraftRequest;
import com.academy.terai.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/drafts")
public class DraftController {
    private final DraftService draftService;

    @Autowired
    public DraftController(DraftService draftService) {
        this.draftService = draftService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addDraft(@RequestBody DraftRequest request) throws ApiRequestException {
        draftService.addDraft(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
