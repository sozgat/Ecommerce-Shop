package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends BaseService<User>, UserDetailsService {
    User setPassword(User user, String rawPassword);
    boolean isUsernameExist(String email);
}
