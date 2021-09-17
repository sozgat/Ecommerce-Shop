package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = ProjectConstants.TableConstants.ROLE_PERMISSION_TABLE_NAME)
public class Permission extends AbstractBaseModel{

    @Size(min = 3, max = 50)
    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Permission() {
    }

    public Permission(String permissionName, Role role) {
        this.permissionName = permissionName;
        this.role = role;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
