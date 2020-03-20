package com.academy.terai.Repository;

import com.academy.terai.Model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Integer> {

}
