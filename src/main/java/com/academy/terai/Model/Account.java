package com.academy.terai.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "account")
public class Account {
    @Id
    private ObjectId id = new ObjectId();
    @NotBlank(message = "Vardas negali būti tuščias")
    @Size(max = 32, message = "Vardas negali būti ilgesnis nei 32 simboliai")
    private String name;
    @NotBlank(message = "Pavardė negali būti tuščia")
    @Size(max = 32, message = "Pavardė negali būti ilgesnė nei 32 simboliai")
    private String lastName;
    @NotEmpty
    @Indexed(unique = true)
    private String emailAddress;
    private Integer reviewedApplications;
    private String lastLoggedIn;
    private String password;
   //@DBRef    Reik pakeist role is string i class
    private String role;

    public Account(String name, String lastName, String emailAddress, Integer reviewedApplications, String lastLoggedIn, String role, String password) {
        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.reviewedApplications = reviewedApplications;
        this.lastLoggedIn = lastLoggedIn;
        this.role = role;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getReviewedApplications() {
        return reviewedApplications;
    }

    public void setReviewedApplications(Integer reviewedApplications) {
        this.reviewedApplications = reviewedApplications;
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
