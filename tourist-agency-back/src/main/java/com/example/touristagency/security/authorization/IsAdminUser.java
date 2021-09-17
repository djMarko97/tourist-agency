package com.example.touristagency.security.authorization;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("(hasRole(T(com.example.touristagency.service.RoleService).ROLE_ADMIN))")
public @interface IsAdminUser {
}
