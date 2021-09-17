package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Role;
import com.ecommerce.shop.business.repository.RoleJPARepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

    private final RoleJPARepository roleJPARepository;

    public RoleServiceImpl(RoleJPARepository roleJPARepository) {
        super(roleJPARepository);
        this.roleJPARepository = roleJPARepository;
    }

    @Override
    public Role findByName(String name) {
        return roleJPARepository.findByName(name).orElse(null);
    }
}
