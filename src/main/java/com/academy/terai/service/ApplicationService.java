package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Application;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.repository.ApplicationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final StatusService statusService;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, StatusService statusService) {
        this.applicationRepository = applicationRepository;
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
        return applicationRepository.findByEmail(email);
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
        if (applicationRepository.findByEmail(application.getEmail()) != null){
            throw new ApiRequestException("Toks email jau egzistuoja: " + application.getEmail());
        }
        application.setDateCreated(new Date());
        //TODO: change the naming convention to ENUM like
        application.setStatus(statusService.findByName("IT akademija gavo formą"));
        return applicationRepository.save(application);
    }
}
