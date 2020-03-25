package com.academy.terai.model.response;

import com.academy.terai.model.Status;
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
    private Date dateCreated;
    private Status status;

}
