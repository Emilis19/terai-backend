package com.academy.terai.Repository;

import com.academy.terai.Model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ApplicationRepository extends MongoRepository<Application, String> {
    Application findByEmail(String email);
 //   Optional<Application> findById(String id);
}
