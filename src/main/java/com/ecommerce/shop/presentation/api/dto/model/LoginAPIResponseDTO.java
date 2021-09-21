package com.ecommerce.shop.presentation.api.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class LoginAPIResponseDTO extends AbstractAPIModelDTO{

    @JsonIgnore
    private String uuid;
    @JsonIgnore
    private long version;

    private String token;
    private String refreshToken;
    private String type = "Basic";
    private String username;
    private Collection<? extends GrantedAuthority> roles;

    public LoginAPIResponseDTO(String token, String type, String username, Collection<? extends GrantedAuthority> roles, String refreshToken) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
        this.refreshToken = refreshToken;
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

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
