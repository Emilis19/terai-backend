package com.academy.terai.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationHrResponse {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateCreated;
    private String status;

}
