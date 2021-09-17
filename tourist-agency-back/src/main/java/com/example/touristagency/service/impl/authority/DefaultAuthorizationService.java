package com.example.touristagency.service.impl.authority;


import com.example.touristagency.entity.authority.RoleEntity;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.service.AuthorizationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DefaultAuthorizationService implements AuthorizationService {
    @Override
    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        return (UserEntity) authentication.getPrincipal();
    }

    @Override
    public Set<RoleEntity> getAuthorizedRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }

        return (Set<RoleEntity>) authentication.getAuthorities();
    }
}
