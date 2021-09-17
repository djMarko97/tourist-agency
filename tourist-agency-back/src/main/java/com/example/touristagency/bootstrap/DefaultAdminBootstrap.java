package com.example.touristagency.bootstrap;

import com.example.touristagency.entity.authority.RoleEntity;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.repository.authority.RoleRepository;
import com.example.touristagency.repository.authority.UserRepository;
import com.example.touristagency.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultAdminBootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String defaultUsername = "admin@admin.com";

    private String defaultPassword = "admin@123";

    private String defaultFirstName = "admin";

    private String defaultLastName = "admin";


    @Override
    public void run(String... args) throws Exception {
        UserEntity adminUser = this.userRepository.findByUsername(this.defaultUsername).orElse(null);
        if(adminUser == null){
            RoleEntity adminRole = this.getOrCreateAdminRole();

            adminUser = this.createDefaultAdminUser(adminRole);

            this.userRepository.save(adminUser);
        }
    }

    private UserEntity createDefaultAdminUser(RoleEntity adminRole) {
        UserEntity adminUser = new UserEntity();
        adminUser.setUsername(this.defaultUsername);
        adminUser.setPassword(this.defaultPassword);
        adminUser.getAuthorities().add(adminRole);
        adminUser.setFirstName(this.defaultFirstName);
        adminUser.setLastName(this.defaultLastName);


        adminUser.setPassword(this.passwordEncoder.encode(this.defaultPassword));

        return adminUser;
    }

    private RoleEntity getOrCreateAdminRole() {

        String adminAuthority = RoleService.PREFIX + RoleService.ROLE_ADMIN;
        RoleEntity adminRole = this.roleRepository.findByAuthority(adminAuthority).orElse(null);
        if(adminRole == null) {
            adminRole = new RoleEntity();
            adminRole.setAuthority(adminAuthority);
            adminRole.setDisplayName(RoleService.DISPLAY_NAME_ADMIN);

            adminRole = this.roleRepository.save(adminRole);
        }

        return adminRole;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setDefaultUsername(String defaultUsername) {
        this.defaultUsername = defaultUsername;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public void setDefaultFirstName(String defaultFirstName) {
        this.defaultFirstName = defaultFirstName;
    }

    public void setDefaultLastName(String defaultLastName) {
        this.defaultLastName = defaultLastName;
    }
}

