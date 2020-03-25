package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
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

    public Status findById(final String id) throws ApiRequestException {
        return statusRepository.findById(id).orElseThrow(() -> new ApiRequestException("Statusas neegzsituoja su id: " + id));
    }


    public Status findByName(final String name) throws ApiRequestException {
        if (statusRepository.findByName(name) == null){
            throw new ApiRequestException("Statusas neegzistuoja su pavadinimu: " + name);
        }
        return statusRepository.findByName(name);
    }
    public void updateStatus(final Status status, final String id) throws ApiRequestException {
        if (!statusRepository.findById(id).isPresent()){
            throw new ApiRequestException("Statusas neegzistuoja su id: " + id);
        }

        statusRepository.save(status);
    }

    public void deleteStatus(final String id) throws ApiRequestException {
        if (!statusRepository.findById(id).isPresent()){
            throw new ApiRequestException("Statusas neegzistuoja su id: " + id);
        }
        statusRepository.deleteById(id);
    }
    public Status addStatus(final Status status)  throws ApiRequestException {
        if (statusRepository.findByName(status.getName()) != null){
            throw new ApiRequestException("Statusas jau egzistuoja: " + status.getName());
        }
        return statusRepository.save(status);
    }
}
