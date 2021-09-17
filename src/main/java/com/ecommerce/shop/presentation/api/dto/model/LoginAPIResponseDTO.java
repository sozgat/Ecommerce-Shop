package com.ecommerce.shop.presentation.api.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LoginAPIResponseDTO extends AbstractAPIModelDTO{

    @JsonIgnore
    private String uuid;
    @JsonIgnore
    private long version;

    private String token;
    private String type = "Basic";
    private String username;
    private List roles;

    public LoginAPIResponseDTO(String token, String type, String username, List roles) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }
}
