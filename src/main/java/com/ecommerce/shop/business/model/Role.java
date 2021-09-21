package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = ProjectConstants.TableConstants.ROLE_TABLE_NAME)
public class Role extends AbstractBaseModel{

    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

   // @Column(name = "permission_name")
    @OneToMany(mappedBy="role", fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
