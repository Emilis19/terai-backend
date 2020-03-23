package com.academy.terai.Repository;

import com.academy.terai.Model.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {


    Account findByEmail(String email);

    List<Account> findAllByOrderByReviewedApplicationsDesc();
}
