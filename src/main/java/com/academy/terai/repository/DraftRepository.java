package com.academy.terai.repository;


import com.academy.terai.model.Draft;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DraftRepository extends MongoRepository<Draft, String> {
    Optional<Draft> findByEmail(String email);
    List<Draft> findAllByDateCreatedIsBeforeAndReminderSentFalse(Date dateBefore);
}
