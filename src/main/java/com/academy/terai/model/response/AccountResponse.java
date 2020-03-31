package com.academy.terai.model.response;

import com.academy.terai.model.Account;
import com.academy.terai.model.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private Integer reviewedApplications;
    private Date lastLoggedIn;
    private List<String> roles = new ArrayList<>();


    public AccountResponse(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.lastLoggedIn =account.getLastLoggedIn();
        this.reviewedApplications = account.getReviewedApplications();
        this.roles = account.getRoles();
    }
}
