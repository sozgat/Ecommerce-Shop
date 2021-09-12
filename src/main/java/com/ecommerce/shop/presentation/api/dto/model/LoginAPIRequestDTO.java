package com.ecommerce.shop.presentation.api.dto.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginAPIRequestDTO {

    @Email
    @NotNull
    @NotEmpty
    private String emailAddress;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
