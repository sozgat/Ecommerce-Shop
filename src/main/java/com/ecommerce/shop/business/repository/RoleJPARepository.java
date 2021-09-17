package com.ecommerce.shop.business.repository;

import com.ecommerce.shop.business.model.Role;

import java.util.Optional;

public interface RoleJPARepository extends BaseJPARepository<Role,Long> {

    Optional<Role> findByName(String name);
}
