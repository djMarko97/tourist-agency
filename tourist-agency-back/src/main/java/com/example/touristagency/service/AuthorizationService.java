package com.example.touristagency.service;

import com.example.touristagency.entity.authority.RoleEntity;
import com.example.touristagency.entity.authority.UserEntity;

import java.util.Set;

public interface AuthorizationService {

    /**
     * Retrieves the currently authenticated user (user that is performing requests)
     * @return UserEntity for authenticated user
     */
    UserEntity getAuthenticatedUser();

    /**
     * Retrieves the authorities of the currently authenticated user
     * @return Set of RoleEntities for authenticated user
     */

    Set<RoleEntity> getAuthorizedRoles();
}
