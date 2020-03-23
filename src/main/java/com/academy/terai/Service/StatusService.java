package com.academy.terai.Service;

import com.academy.terai.Model.Status;
import com.academy.terai.Repository.StatusRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Status> findById(final String id) {
        return statusRepository.findById(id);
    }

    public Status findByName(final String name) {
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
    public Status addStatus(final Status status)  throws NotFoundException {
        if (statusRepository.findByName(status.getName()) != null){
            throw new NotFoundException(status.getName());
        }
        return statusRepository.save(status);
    }
}
