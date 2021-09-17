package com.example.touristagency.service.impl.authority;

import com.example.touristagency.entity.authority.RoleEntity;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.repository.authority.RoleRepository;
import com.example.touristagency.repository.authority.UserRepository;
import com.example.touristagency.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class DefaultRoleService implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public DefaultRoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity addUserToRole(Long userId, String role) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(role);

        if(!StringUtils.startsWith(role, PREFIX)){
            role = PREFIX + role;
        }

        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Could nof find user with that id"));

        RoleEntity roleEntity = this.roleRepository.findByAuthority(role)
                .orElseThrow(() -> new EntityNotFoundException("Could not find role with that name"));

        userEntity.getAuthorities().add(roleEntity);

        return this.userRepository.save(userEntity);
    }
}
