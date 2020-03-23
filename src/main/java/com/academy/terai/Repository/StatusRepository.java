package com.academy.terai.Repository;


import com.academy.terai.Model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StatusRepository extends MongoRepository<Status, String>  {
    Status findByName(String name);
}
