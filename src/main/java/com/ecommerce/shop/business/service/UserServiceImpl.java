package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.User;
import com.ecommerce.shop.business.repository.UserJPARepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    private final UserJPARepository userJPARepository;

    public UserServiceImpl(UserJPARepository userJPARepository) {
        super(userJPARepository);
        this.userJPARepository = userJPARepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJPARepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
