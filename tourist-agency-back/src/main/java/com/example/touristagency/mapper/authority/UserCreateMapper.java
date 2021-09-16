package com.example.touristagency.mapper.authority;


import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.mapper.MyMapper;
import com.example.touristagency.mapper.ReservationMapper;
import com.example.touristagency.requests.authority.CreateUserRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between User Entity and Create User Request
 * @author djMarko97
 */
@Mapper(componentModel = "spring", uses = ReservationMapper.class, builder = @Builder(disableBuilder = true))
public interface UserCreateMapper extends MyMapper<UserEntity, CreateUserRequest> {
}
