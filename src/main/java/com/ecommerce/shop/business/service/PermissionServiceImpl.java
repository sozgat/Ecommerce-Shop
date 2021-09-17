package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Permission;
import com.ecommerce.shop.business.repository.PermissionJPARepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{

    private final PermissionJPARepository permissionJPARepository;

    public PermissionServiceImpl(PermissionJPARepository permissionJPARepository) {
        super(permissionJPARepository);
        this.permissionJPARepository = permissionJPARepository;
    }


    @Override
    public Permission findByPermissionName(String permissionName) {
        return permissionJPARepository.findByAndPermissionName(permissionName).orElse(null);
    }
}
