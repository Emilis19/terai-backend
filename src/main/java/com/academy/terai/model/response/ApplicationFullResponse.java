package com.academy.terai.model.response;

import com.academy.terai.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationFullResponse {

    private String id;
    private String firstName;
    private String lastName;
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
    private String status;

    public ApplicationFullResponse(Application application) {
        this.id = application.getId();
        this.firstName = application.getFirstName();
        this.lastName = application.getLastName();
        this.email = application.getEmail();
        this.academyTime = application.isAcademyTime();
        this.contractAgreement = application.isContractAgreement();
        this.contractReason = application.getContractReason();
        this.likedTechnologies = application.getLikedTechnologies();
        this.reasonForApplying = application.getReasonForApplying();
        this.school = application.getSchool();
        this.degree = application.getDegree();
        this.mobileNumber = application.getMobileNumber();
        this.linkedinUrl = application.getLinkedinUrl();
        this.image = application.getImage();
        this.hobbies = application.getHobbies();
        this.referenceToIt = application.getReferenceToIt();
        this.dateCreated = application.getDateCreated();
        this.status = application.getStatus();
    }
}
