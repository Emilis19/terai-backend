package com.academy.terai.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DraftRequest {
    private String firstName;
    @NotBlank(message = "Email negali būti tuščias")
    private String email;
}
