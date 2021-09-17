package com.ecommerce.shop.presentation.api.controller;

import com.ecommerce.shop.business.model.User;
import com.ecommerce.shop.business.util.JwtTokenUtil;
import com.ecommerce.shop.presentation.api.dto.APIResponseDTO;
import com.ecommerce.shop.presentation.api.dto.model.LoginAPIRequestDTO;
import com.ecommerce.shop.presentation.api.dto.model.LoginAPIResponseDTO;
import com.ecommerce.shop.presentation.api.dto.model.ProductAPIResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtTokenUtil jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtTokenUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("login")
    public ResponseEntity<APIResponseDTO<LoginAPIResponseDTO>> authenticateUser(@RequestBody LoginAPIRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        APIResponseDTO<LoginAPIResponseDTO> apiResponse = new APIResponseDTO<>(HttpStatus.OK,
                new LoginAPIResponseDTO(jwt,"Bearer",
                userDetails.getUsername(),
                roles));

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
