package com.example.touristagency.service;

import com.example.touristagency.dto.ReservationDto;
import com.example.touristagency.dto.UserDto;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.requests.authority.CreateUserRequest;

import java.util.Set;

public interface UserService {

    /**
     * Creates user from createUserRequest
     * @param createUserRequest userRequest to save
     * @return User object
     */

    UserDto createUser(CreateUserRequest createUserRequest);

    /**
     * Updates userEntity object
     * @param userEntity userEntity to update
     * @return User entity
     */

    UserEntity save(UserEntity userEntity);

    /**
     * Finds user  by id
     * @param id id of the user for search
     * @return User entity
     */

    UserEntity findById(Long id);

    Set<ReservationDto> getReservationsByUserId(Long userId);

    Set<ReservationDto> addReservationToUser(Long idUser, Long idRes);

    Set<ReservationDto> removeReservationForUser(Long idUser, Long idRes);
}
