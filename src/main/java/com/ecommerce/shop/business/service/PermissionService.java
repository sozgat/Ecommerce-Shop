package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Permission;

public interface PermissionService extends BaseService<Permission> {

    Permission findByPermissionName(String permissionName);

}
