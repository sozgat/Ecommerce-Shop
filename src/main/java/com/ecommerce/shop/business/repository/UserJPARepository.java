package com.ecommerce.shop.business.repository;

import com.ecommerce.shop.business.model.User;

import java.util.Optional;

public interface UserJPARepository extends BaseJPARepository<User,Long> {

    Optional<User> findByEmail(String email);

}
