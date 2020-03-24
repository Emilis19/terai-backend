package com.academy.terai.Repository;

import com.academy.terai.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
