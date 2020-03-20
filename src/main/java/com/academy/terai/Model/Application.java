package com.academy.terai.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "application")
public class Application {
    @Id
    private String id;


}
