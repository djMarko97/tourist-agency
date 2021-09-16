package com.example.touristagency.repository.authority;

import com.example.touristagency.entity.authority.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository
 * @author djMarko97
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Find user by username
     * @param username username
     * @return optional of User entity
     */

    Optional<UserEntity> findByUsername(String username);
}
