package com.example.touristagency.service;

import com.example.touristagency.entity.authority.UserEntity;

public interface RoleService {

    /** User Role **/
    String ROLE_USER = "USER";

    /** Admin Role **/
    String ROLE_ADMIN = "ADMIN";

    /** Display name for user role **/
    String DISPLAY_NAME_USER = "User";

    /** Display name for admin role **/
    String DISPLAY_NAME_ADMIN = "Admin";

    /** Role prefix **/
    String PREFIX = "ROLE_";

    UserEntity addUserToRole(Long userId, String role);
}
