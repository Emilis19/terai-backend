package com.academy.terai.Repository;

import com.academy.terai.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository <User, Integer> {

}
