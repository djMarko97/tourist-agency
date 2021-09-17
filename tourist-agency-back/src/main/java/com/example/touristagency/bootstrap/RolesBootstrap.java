package com.example.touristagency.bootstrap;

import com.example.touristagency.entity.authority.RoleEntity;
import com.example.touristagency.repository.authority.RoleRepository;
import com.example.touristagency.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesBootstrap implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        this.createIfNotExists(RoleService.ROLE_ADMIN, RoleService.DISPLAY_NAME_ADMIN);
        this.createIfNotExists(RoleService.ROLE_USER, RoleService.DISPLAY_NAME_USER);
    }

    private void createIfNotExists(String authority, String displayName) {
        if(!StringUtils.startsWith(authority, RoleService.PREFIX)) {
            authority = RoleService.PREFIX + authority;
        }

        RoleEntity roleEntity = this.roleRepository.findByAuthority(authority).orElse(null);
        if(roleEntity == null) {
            roleEntity = new RoleEntity();
            roleEntity.setAuthority(authority);
            roleEntity.setDisplayName(displayName);

            this.roleRepository.save(roleEntity);
        }
    }
}
