package com.academy.terai.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Document(collection = "account")
@Data
public class Account implements UserDetails {
    @Id
    private String id;
    @NotBlank(message = "Vardas negali būti tuščias")
    @Size(max = 32, message = "Vardas negali būti ilgesnis nei 32 simboliai")
    private String name;
    @NotBlank(message = "Pavardė negali būti tuščia")
    @Size(max = 32, message = "Pavardė negali būti ilgesnė nei 32 simboliai")
    private String lastName;
    @Indexed(unique = true)
    @NotBlank(message = "El. paštas negali būti tuščias")
    private String email;
    private Integer reviewedApplications;
    private Date lastLoggedIn;
    @NotBlank(message = "Slaptažodis negali būti tuščias")
    private String password;
    //@DBRef
    private List<String> roles = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getReviewedApplications() {
        return reviewedApplications;
    }

    public void setReviewedApplications(Integer reviewedApplications) {
        this.reviewedApplications = reviewedApplications;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
