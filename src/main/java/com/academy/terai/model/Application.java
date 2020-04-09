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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "application")
public class Application {
    @Id
    private String id;
    @NotBlank(message = "Vardas negali būti tuščias")
    @Size(max = 32, message = "Vardas negali būti ilgesnis nei 32 simboliai")
    private String firstName;

    @NotBlank(message = "Pavardė negali būti tuščia")
    @Size(max = 32, message = "Pavardė negali būti ilgesnė nei 32 simboliai")
    private String lastName;

    @NotBlank(message = "Email negali būti tuščias")
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Akademijos laikas negali būti tuščias")
    private boolean academyTime;

    private String academyTimeReason;

    @NotNull(message = "Sutikimas negali būti tuščias")
    private boolean contractAgreement;

    private String contractReason;

    @NotBlank(message = "Technologijos negali būti tuščia")
    @Size(max = 256, message = "Technologijos negali būti ilgesnė nei 256 simboliai")
    private String likedTechnologies;

    @NotBlank(message = "Priežastis negali būti tuščia")
    @Size(max = 256, message = "Priežastis negali būti ilgesnė nei 256 simboliai")
    private String reasonForApplying;
    private String school;
    private String degree;
    private String mobileNumber;
    private String projectUrl;
    private String linkedinUrl;
    private String image;
    private String hobbies;
    private String referenceToIt;
    private Date dateCreated;
    private String status;

    private String password;
    private List<Comment> comment;

    public Application (ApplicationRequest applicationRequest){
        this.firstName = applicationRequest.getFirstName();
        this.lastName = applicationRequest.getLastName();
        this.email = applicationRequest.getEmail();
        this.academyTime = applicationRequest.isAcademyTime();
        this.academyTimeReason = applicationRequest.getAcademyTimeReason();
        this.contractAgreement = applicationRequest.isContractAgreement();
        this.contractReason = applicationRequest.getContractReason();
        this.likedTechnologies = applicationRequest.getLikedTechnologies();
        this.reasonForApplying = applicationRequest.getReasonForApplying();
        this.school = applicationRequest.getSchool();
        this.degree = applicationRequest.getDegree();
        this.mobileNumber = applicationRequest.getMobileNumber();
        this.linkedinUrl = applicationRequest.getLinkedinUrl();
        this.projectUrl = applicationRequest.getProjectUrl();
        this.image = applicationRequest.getImage();
        this.hobbies = applicationRequest.getHobbies();
        this.referenceToIt = applicationRequest.getReferenceToIt();
        this.comment = new ArrayList();
    }

    public void changeApp (ApplicationUpdateRequest applicationRequest){
        this.firstName = applicationRequest.getFirstName();
        this.lastName = applicationRequest.getLastName();
        this.academyTime = applicationRequest.isAcademyTime();
        this.academyTimeReason = applicationRequest.getAcademyTimeReason();
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
