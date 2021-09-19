package com.ecommerce.shop.presentation.api.dto.model;

import javax.validation.constraints.NotBlank;

public class RefreshTokenAPIRequestDTO {

    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
