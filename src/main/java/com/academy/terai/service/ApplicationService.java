package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Application;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.repository.ApplicationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JavaMailSender javaMailSender;
    private final StatusService statusService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, JavaMailSender javaMailSender, StatusService statusService) {
        this.applicationRepository = applicationRepository;
        this.javaMailSender = javaMailSender;
        this.statusService = statusService;
    }

    public List<ApplicationHrResponse> findAll() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationHrResponse> responseList = new ArrayList<>();

        for (Application app : applications) {
            responseList.add(new ApplicationHrResponse(app.getId(), app.getFirstName(), app.getDateCreated(), app.getStatus()));
        }
        return responseList;
    }


    public Application findById(final String id) throws ApiRequestException {
        return applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
    }

    public Application findByEmail(final String email) {
        return applicationRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }

    public void updateApplication(final Application application, final String id) throws ApiRequestException {
        //change to orelsethrow
        if (!applicationRepository.findById(id).isPresent()){
            throw new ApiRequestException("Aplikacija neegzistuoja su id: " + id);
    }

        applicationRepository.save(application);
    }

    public void deleteApplication(final String id) throws ApiRequestException {
        if (!applicationRepository.findById(id).isPresent()){
            throw new ApiRequestException("Aplikacija neegzistuoja su id: " + id);
        }
        applicationRepository.deleteById(id);
    }
    public Application addApplication(final Application application) throws ApiRequestException {
        if (applicationRepository.findByEmail(application.getEmail()).isPresent()){
            throw new ApiRequestException("Toks email jau egzistuoja: " + application.getEmail());
        }
        String password = UUID.randomUUID().toString();
        application.setDateCreated(new Date());
        application.setPassword(passwordEncoder.encode(password));
        //TODO: change the naming convention to ENUM like
        application.setStatus(statusService.findByName("IT akademija gavo formą"));

        Application returnApplication = applicationRepository.save(application);

        if (returnApplication != null){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(returnApplication.getEmail());

            msg.setSubject("Akademija");
            msg.setText("Sveiki " + returnApplication.getFirstName() + "\n Forma galite perziureti: localhost:4200/" + returnApplication.getId()
                    + "\n Jusu slaptazodis: " + password);

            javaMailSender.send(msg);
        }

        return returnApplication;
    }
}
