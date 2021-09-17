package com.ecommerce.shop.business.repository;

import com.ecommerce.shop.business.model.Permission;

import java.util.Optional;

public interface PermissionJPARepository extends BaseJPARepository<Permission,Long> {

    Optional<Permission> findByAndPermissionName(String permissionName);
}
