package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = ProjectConstants.TableConstants.USER_TABLE_NAME)
public class User extends AbstractBaseModel implements UserDetails {

    public interface UserValidationForm {}
    public interface UserValidationEdit {}
    public interface UserValidationPassword {}

    // @Size(min = 3, max = 50, groups = {UserValidationForm.class, UserValidationEdit.class})
    @Column(name = "first_name", nullable = false)
    private String firstName;

    //@Size(min = 3, max = 50, groups = {UserValidationForm.class, UserValidationEdit.class})
    @Column(name = "last_name", nullable = false)
    private String lastName;

    // @Email(groups = {UserValidationForm.class, UserValidationEdit.class})
    @Column(name = "email")
    private String email;

    //@NotEmpty(groups = {UserValidationForm.class, UserValidationPassword.class})
    @Column(name = "password", nullable = false)
    private String password;

    // @NotNull(groups = {UserValidationForm.class, UserValidationEdit.class})
    //@ManyToOne
    //@JoinColumn(name = "role_id", nullable = false)
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    //    List<GrantedAuthority> authorities = new ArrayList<>();
    //    for (String permission : this.role.getPermissions()) {
    //        authorities.add(new SimpleGrantedAuthority(permission));
    //    }
        return  this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    //    return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }


}