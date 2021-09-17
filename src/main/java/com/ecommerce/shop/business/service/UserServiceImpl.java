package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.User;
import com.ecommerce.shop.business.repository.UserJPARepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userJPARepository;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserJPARepository userJPARepository) {
        super(userJPARepository);
        this.passwordEncoder = passwordEncoder;
        this.userJPARepository = userJPARepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJPARepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public boolean isUsernameExist(String email) {
        return userJPARepository.existsByEmail(email);
    }

    @Override
    public User setPassword(User user, String rawPassword) {
        User storedUser = this.findById(user.getId());
        storedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJPARepository.save(storedUser);
    }


    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
        }
        return userJPARepository.save(user);
    }

}
