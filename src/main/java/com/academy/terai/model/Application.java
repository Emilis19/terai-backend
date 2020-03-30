package com.academy.terai.model;

import com.academy.terai.model.request.ApplicationRequest;
import com.academy.terai.model.request.ApplicationUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "application")
public class Application {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Email
    @Indexed(unique = true)
    private String email;

    private boolean academyTime;
    private boolean contractAgreement;
    private String contractReason;
    private String likedTechnologies;
    private String reasonForApplying;
    private String school;
    private String degree;
    private String mobileNumber;
    private String linkedinUrl;
    private Binary image;
    private String hobbies;
    private String referenceToIt;
    private Date dateCreated;
    private UUID password;
    private String status;

    public Application (ApplicationRequest applicationRequest){
        this.firstName = applicationRequest.getFirstName();
        this.lastName = applicationRequest.getLastName();
        this.email = applicationRequest.getEmail();
        this.academyTime = applicationRequest.isAcademyTime();
        this.contractAgreement = applicationRequest.isContractAgreement();
        this.contractReason = applicationRequest.getContractReason();
        this.likedTechnologies = applicationRequest.getLikedTechnologies();
        this.reasonForApplying = applicationRequest.getReasonForApplying();
        this.school = applicationRequest.getSchool();
        this.degree = applicationRequest.getDegree();
        this.mobileNumber = applicationRequest.getMobileNumber();
        this.linkedinUrl = applicationRequest.getLinkedinUrl();
        this.image = applicationRequest.getImage();
        this.hobbies = applicationRequest.getHobbies();
        this.referenceToIt = applicationRequest.getReferenceToIt();
    }

    public void changeApp (ApplicationUpdateRequest applicationRequest){
        this.firstName = applicationRequest.getFirstName();
        this.lastName = applicationRequest.getLastName();
        this.academyTime = applicationRequest.isAcademyTime();
        this.contractAgreement = applicationRequest.isContractAgreement();
        this.contractReason = applicationRequest.getContractReason();
        this.likedTechnologies = applicationRequest.getLikedTechnologies();
        this.reasonForApplying = applicationRequest.getReasonForApplying();
        this.school = applicationRequest.getSchool();
        this.degree = applicationRequest.getDegree();
        this.mobileNumber = applicationRequest.getMobileNumber();
        this.linkedinUrl = applicationRequest.getLinkedinUrl();
        this.image = applicationRequest.getImage();
        this.hobbies = applicationRequest.getHobbies();
        this.referenceToIt = applicationRequest.getReferenceToIt();
    }

}
