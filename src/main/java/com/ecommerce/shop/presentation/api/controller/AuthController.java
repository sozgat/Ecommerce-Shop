package com.ecommerce.shop.presentation.api.controller;

import com.ecommerce.shop.business.model.User;
import com.ecommerce.shop.business.service.UserService;
import com.ecommerce.shop.business.util.JwtTokenUtil;
import com.ecommerce.shop.presentation.api.dto.APIResponseDTO;
import com.ecommerce.shop.presentation.api.dto.model.LoginAPIRequestDTO;
import com.ecommerce.shop.presentation.api.dto.model.LoginAPIResponseDTO;
import com.ecommerce.shop.presentation.api.dto.model.RefreshTokenAPIRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtTokenUtil jwtUtils,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<APIResponseDTO<LoginAPIResponseDTO>> authenticateUser(@RequestBody LoginAPIRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userPrincipal = (User) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userPrincipal.getUsername());
        String refreshJwtToken = jwtUtils.generateRefreshJwtToken(userPrincipal.getUsername());

        User userDetails = (User) authentication.getPrincipal();

        APIResponseDTO<LoginAPIResponseDTO> apiResponse = new APIResponseDTO<>(HttpStatus.OK,
                new LoginAPIResponseDTO(jwt,"Bearer",
                userDetails.getUsername(),
                userDetails.getAuthorities(), refreshJwtToken));

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenAPIRequestDTO refreshTokenAPIRequestDTO) {
        String requestRefreshToken = refreshTokenAPIRequestDTO.getRefreshToken();
        String userName = jwtUtils.getUsername(requestRefreshToken);

        String jwt = jwtUtils.generateJwtToken(userName);
        String refreshJwtToken = jwtUtils.generateRefreshJwtToken(userName);

        UserDetails userDetails = userService.loadUserByUsername(userName);

        APIResponseDTO<LoginAPIResponseDTO> apiResponse = new APIResponseDTO<>(HttpStatus.OK,
        new LoginAPIResponseDTO(jwt,"Bearer",
                userName,
                userDetails.getAuthorities(), refreshJwtToken));

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    /*
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateJwtToken(user.getUsername());
                    List<String> roles = user.getAuthorities().stream()
                            .map(item -> item.getAuthority())
                            .collect(Collectors.toList());

                    return ResponseEntity.ok(new LoginAPIResponseDTO(token, "Bearer",
                            user.getUsername(),
                            roles,
                            requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
        }
        */

}
