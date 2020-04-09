package com.academy.terai.model;

import com.academy.terai.model.request.DraftRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "draft")
public class Draft {
    @Id
    private String id;
    private String firstName;

    @Email
    @Indexed(unique = true)
    @NotBlank(message = "Email negali būti tuščias")
    private String email;
    private Date dateCreated;
    private Boolean reminderSent;

    public Draft(DraftRequest request) {
        this.firstName = request.getFirstName();
        this.email = request.getEmail();
    }
}
