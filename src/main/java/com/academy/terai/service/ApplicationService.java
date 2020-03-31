package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Application;
import com.academy.terai.model.ApplicationStatus;
import com.academy.terai.model.request.ApplicationRequest;
import com.academy.terai.model.request.ApplicationUpdateRequest;
import com.academy.terai.model.response.ApplicationFullResponse;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, JavaMailSender javaMailSender, PasswordEncoder passwordEncoder) {
        this.applicationRepository = applicationRepository;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ApplicationHrResponse> findAll() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationHrResponse> responseList = new ArrayList<>();

        for (Application app : applications) {
            responseList.add(new ApplicationHrResponse(app.getId(), app.getFirstName(), app.getDateCreated(), app.getStatus()));
        }
        return responseList;
    }


    public ApplicationFullResponse findById(final String id) throws ApiRequestException {
        Application app = applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
        ApplicationFullResponse appResponse = new ApplicationFullResponse(app);
        return appResponse;
    }

    public Application findByEmail(final String email) {
        return applicationRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su el. pastu: " + email));
    }

    public void updateApplication(final ApplicationUpdateRequest application, final String id) throws ApiRequestException {
        Application appToChange = applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
        appToChange.changeApp(application);
        appToChange.setStatus(ApplicationStatus.FORM_RECEIVED.getName());
        appToChange.setDateCreated(new Date());
        applicationRepository.save(appToChange);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appToChange.getEmail());
        msg.setSubject("Akademija");
        msg.setText("Sveiki " + appToChange.getFirstName() + "\n Forma galite perziureti: localhost:4200/" + appToChange.getId()
                + "\n Jusu slaptazodis: " + appToChange.getPassword());
        javaMailSender.send(msg);
    }

    public void deleteApplication(final String id) throws ApiRequestException {
        if (!applicationRepository.findById(id).isPresent()){
            throw new ApiRequestException("Aplikacija neegzistuoja su id: " + id);
        }
        applicationRepository.deleteById(id);
    }
    public ApplicationFullResponse addApplication(final ApplicationRequest application) throws ApiRequestException {
        if (applicationRepository.findByEmail(application.getEmail()).isPresent()){
            throw new ApiRequestException("Toks email jau egzistuoja: " + application.getEmail());
        }
        String password = UUID.randomUUID().toString();
        Application app = new Application(application);
        app.setDateCreated(new Date());
        app.setPassword(passwordEncoder.encode(password));
        app.setStatus(ApplicationStatus.FORM_RECEIVED.getName());
        ApplicationFullResponse returnApplication = new ApplicationFullResponse(applicationRepository.save(app));

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(returnApplication.getEmail());

            msg.setSubject("Akademija");
            msg.setText("Sveiki " + returnApplication.getFirstName() + "\n Forma galite perziureti: localhost:4200/" + returnApplication.getId()
                    + "\n Jusu slaptazodis: " + password);

            javaMailSender.send(msg);


        return returnApplication;
    }
}
