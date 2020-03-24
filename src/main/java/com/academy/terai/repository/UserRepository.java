package com.academy.terai.repository;

import com.academy.terai.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository <User, Integer> {

}
