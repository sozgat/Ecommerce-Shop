package com.ecommerce.shop.business.context.listener;

import com.ecommerce.shop.business.model.Permission;
import com.ecommerce.shop.business.model.Role;
import com.ecommerce.shop.business.model.User;
import com.ecommerce.shop.business.service.PermissionService;
import com.ecommerce.shop.business.service.RoleService;
import com.ecommerce.shop.business.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(UserApplicationListener.class);
    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;

    public UserApplicationListener(UserService userService, RoleService roleService,
                                   PermissionService permissionService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            List<User> userAll = userService.findAll();
            if (userAll.isEmpty()) {

                InputStream inputStream = new ClassPathResource("db/users.csv").getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] rawValues = line.split("\\,");

                    User user = new User();
                    user.setUsername(rawValues[0]);
                    user.setFirstName(rawValues[1]);
                    user.setLastName(rawValues[2]);
                    user.setPassword( passwordEncoder.encode(rawValues[3]) );
                    HashSet<Role> roles = Arrays.stream(rawValues[4].split("\\|"))
                            .map(m -> {
                                String name = "ROLE_" + m.toUpperCase(Locale.ROOT);
                                Role role = roleService.findByName(name);
                                if(null == role){
                                    role = roleService.save(new Role(name));
                                }
                                Role finalRole = role;
                                ArrayList<Permission> permissions = Arrays.stream(rawValues[5].split("\\|"))
                                    .map(pName -> {
                                        Permission permission = permissionService.findByPermissionName(pName);
                                        if(null == permission){
                                            permission = permissionService.save(new Permission(pName, finalRole));
                                        }
                                        return permission;
                                    })
                                    .collect(Collectors.toCollection(ArrayList::new));
                                role.getPermissions().addAll(permissions);

                                return role;
                            })
                            .collect(Collectors.toCollection(HashSet::new));

                    user.setRoles(roles);
                    user.setActive(true);

                    userService.save(user);
                }
            }

        } catch (Exception e) {
            log.error("Application has encountered an error!", e);
        }
    }
}
