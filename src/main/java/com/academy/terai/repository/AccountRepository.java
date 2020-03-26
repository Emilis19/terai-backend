package com.academy.terai.repository;

import com.academy.terai.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByEmail(String email);

    List<Account> findAllByOrderByReviewedApplicationsDesc();
}
