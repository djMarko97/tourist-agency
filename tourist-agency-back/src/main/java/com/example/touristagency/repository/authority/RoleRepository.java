package com.example.touristagency.repository.authority;


import com.example.touristagency.entity.authority.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Role Repository
 * @author djMarko97
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Find role by authority
     * @param authority authority
     * @return optional of RoleEntity
     */
    Optional<RoleEntity> findByAuthority(String authority);

    /**
     * Find role by display name
     * @param displayName displayName
     * @return optional of RoleEntity
     */
    Optional<RoleEntity> findByDisplayName(String displayName);
}
