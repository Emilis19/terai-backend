package com.academy.terai.controllers;

import com.academy.terai.model.Account;
import com.academy.terai.model.Application;
import com.academy.terai.model.response.AuthenticationResponse;
import com.academy.terai.repository.AccountRepository;
import com.academy.terai.repository.ApplicationRepository;
import com.academy.terai.security.AuthenticationRequest;
import com.academy.terai.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AccountRepository users;
    @Autowired
    ApplicationRepository applicants;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Application application;
            Account account;
            String token;
            String signeeRole;
            List<String> roles;
            String id;
            if (applicants.findByEmail(username).isPresent()) {
                application = this.applicants.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
                roles = new ArrayList<>();
                signeeRole = "application";
                id = application.getId();
            } else {
                account = this.users.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
                roles = account.getRoles();
                if(roles.contains("ROLE_ADMIN")){
                    signeeRole = "admin";
                }else{
                    signeeRole = "hr";
                }
                id = account.getId();
            }
            token = jwtTokenProvider.createToken(username, roles);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(id, signeeRole, token);
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
