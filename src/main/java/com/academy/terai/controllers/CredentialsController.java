package com.academy.terai.controllers;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.request.ForgotPasswordRequest;
import com.academy.terai.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/credentials")
public class CredentialsController {
    private final CredentialService credentialService;

    public CredentialsController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<HttpStatus> sendNewPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws ApiRequestException {
        credentialService.sendNewPassword(forgotPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}