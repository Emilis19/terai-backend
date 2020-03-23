package com.academy.terai.config;

import com.academy.terai.Model.Account;
import com.academy.terai.Model.User;
import com.academy.terai.Repository.AccountRepository;
import com.academy.terai.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, AccountRepository accountRepository) {
        return strings -> {
//            userRepository.save(new User("Peter", "Peterson"));
//            userRepository.save(new User("Sam", "Samson"));
      //      accountRepository.save(new Account("test", "tester", "test@gmail.com", 0, "date", "tester", "none"));
        };
    }
}
