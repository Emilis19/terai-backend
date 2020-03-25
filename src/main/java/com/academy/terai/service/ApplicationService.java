package com.academy.terai.service;

import com.academy.terai.model.Application;
import com.academy.terai.repository.ApplicationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
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
    public ApplicationService(ApplicationRepository applicationRepository, JavaMailSender javaMailSender, StatusService statusService) {
        this.applicationRepository = applicationRepository;
        this.javaMailSender = javaMailSender;
        this.statusService = statusService;
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }


    public Application findById(final String id) throws NotFoundException {
        return applicationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Application findByEmail(final String email) {
        return applicationRepository.findByEmail(email);
    }

    public void updateApplication(final Application application, final String id) throws NotFoundException {
        //change to orelsethrow
        if (!applicationRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }

        applicationRepository.save(application);
    }

    public void deleteApplication(final String id) throws NotFoundException {
        if (!applicationRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }
        applicationRepository.deleteById(id);
    }
    public Application addApplication(final Application application) throws KeyAlreadyExistsException, NotFoundException {
        if (applicationRepository.findByEmail(application.getEmail()) != null){
            throw new KeyAlreadyExistsException(application.getEmail());
        }
        application.setDateCreated(new Date());
        application.setPassword(UUID.randomUUID());
        //TODO: change the naming convention to ENUM like
        application.setStatus(statusService.findByName("IT akademija gavo formą"));

        Application returnApplication = applicationRepository.save(application);

        if (returnApplication != null){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(returnApplication.getEmail());

            msg.setSubject("Akademija");
            msg.setText("Sveiki " + returnApplication.getFirstName() + "\n Forma galite perziureti: localhost:4200/" + returnApplication.getId()
                    + "\n Jusu slaptazodis: " + returnApplication.getPassword());

            javaMailSender.send(msg);
        }

        return returnApplication;
    }
}
