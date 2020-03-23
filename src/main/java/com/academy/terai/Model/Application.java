package com.academy.terai.Model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
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

    @NotNull(message = "Sutikimas negali būti tuščias")
    private boolean contractAgreement;

    @NotBlank(message = "Priežastis negali būti tuščia")
    @Size(max = 256, message = "Priežastis negali būti ilgesnė nei 256 simboliai")
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
    private String linkedinUrl;
    private Binary image;
    private String hobbies;
    private String referenceToIt;
    private Date dateCreated;
    private Status status;


    @javax.persistence.Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAcademyTime() {
        return academyTime;
    }

    public void setAcademyTime(boolean academyTime) {
        this.academyTime = academyTime;
    }

    public boolean isContractAgreement() {
        return contractAgreement;
    }

    public void setContractAgreement(boolean contractAgreement) {
        this.contractAgreement = contractAgreement;
    }

    public String getContractReason() {
        return contractReason;
    }

    public void setContractReason(String contractReason) {
        this.contractReason = contractReason;
    }

    public String getLikedTechnologies() {
        return likedTechnologies;
    }

    public void setLikedTechnologies(String likedTechnologies) {
        this.likedTechnologies = likedTechnologies;
    }

    public String getReasonForApplying() {
        return reasonForApplying;
    }

    public void setReasonForApplying(String reasonForApplying) {
        this.reasonForApplying = reasonForApplying;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getReferenceToIt() {
        return referenceToIt;
    }

    public void setReferenceToIt(String referenceToIt) {
        this.referenceToIt = referenceToIt;
    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
