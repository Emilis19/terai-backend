package com.academy.terai.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {
    @Id
    private String id;
    private String role;

    public void Role(String role, String id) {
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

}
