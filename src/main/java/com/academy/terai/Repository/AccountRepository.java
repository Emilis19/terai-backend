package com.academy.terai.Repository;

import com.academy.terai.Model.Account;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByEmail(String email);

    List<Account> findAllByOrderByReviewedApplicationsDesc();
}
