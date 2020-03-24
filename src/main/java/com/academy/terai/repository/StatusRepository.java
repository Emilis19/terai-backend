package com.academy.terai.repository;


import com.academy.terai.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StatusRepository extends MongoRepository<Status, String>  {
    Status findByName(String name);
}
