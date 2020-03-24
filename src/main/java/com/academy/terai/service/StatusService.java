package com.academy.terai.service;

import com.academy.terai.model.Status;
import com.academy.terai.repository.StatusRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;
@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status findById(final String id) throws NotFoundException {
        return statusRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Status findByName(final String name) throws NotFoundException {
        if (statusRepository.findByName(name) == null){
            throw new NotFoundException(name);
        }
        return statusRepository.findByName(name);
    }
    public void updateStatus(final Status status, final String id) throws NotFoundException {
        if (!statusRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }

        statusRepository.save(status);
    }

    public void deleteStatus(final String id) throws NotFoundException {
        if (!statusRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }
        statusRepository.deleteById(id);
    }
    public Status addStatus(final Status status)  throws KeyAlreadyExistsException {
        if (statusRepository.findByName(status.getName()) != null){
            throw new KeyAlreadyExistsException(status.getName());
        }
        return statusRepository.save(status);
    }
}
