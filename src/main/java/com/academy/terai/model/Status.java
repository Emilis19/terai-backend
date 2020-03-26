package com.academy.terai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "status")
public class Status {
    @Id
    private String id;
    private String name;


}
