package com.academy.terai.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

import org.springframework.data.mongodb.core.index.Indexed;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @NotBlank(message = "Vardas negali būti tuščias")
    @Size(max = 32, message = "Vardas negali būti ilgesnis nei 32 simboliai")
    private String firstName;

    @NotBlank(message = "Pavardė negali būti tuščia")
    @Size(max = 32, message = "Pavardė negali būti ilgesnė nei 32 simboliai")
    private String lastName;

    @NotBlank(message = "Email negali būti tuščias")
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
    private String linkedinUrl;
    private Binary image;
    private String hobbies;
    private String referenceToIt;

}
