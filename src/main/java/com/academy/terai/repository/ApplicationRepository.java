package com.academy.terai.repository;

import com.academy.terai.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ApplicationRepository extends MongoRepository<Application, String> {
    Application findByEmail(String email);
 //   Optional<Application> findById(String id);
}
