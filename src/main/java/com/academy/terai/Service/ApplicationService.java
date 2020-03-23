package com.academy.terai.Service;

import com.academy.terai.Model.Application;
import com.academy.terai.Repository.ApplicationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final StatusService statusService;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, StatusService statusService) {
        this.applicationRepository = applicationRepository;
        this.statusService = statusService;
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Optional<Application> findById(final String id) {
        return applicationRepository.findById(id);
    }

    public Application findByEmail(final String email) {
        return applicationRepository.findByEmail(email);
    }

    public void updateApplication(final Application application, final String id) throws NotFoundException {
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
    public Application addApplication(final Application application) throws NotFoundException {
        if (applicationRepository.findByEmail(application.getEmail()) != null){
            throw new NotFoundException(application.getEmail());
        }
        application.setDateCreated(new Date());
        application.setStatus(statusService.findByName("IT akademija gavo formą"));
        return applicationRepository.save(application);
    }
}
