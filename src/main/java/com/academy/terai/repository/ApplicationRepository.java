package com.academy.terai.repository;

import com.academy.terai.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ApplicationRepository extends MongoRepository<Application, String> {
    Optional<Application> findByEmail(String email);
 //   Optional<Application> findById(String id);
}
