package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Role;

public interface RoleService extends BaseService<Role> {

    Role findByName(String name);

}
