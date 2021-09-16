package com.example.touristagency.mapper.authority;

import com.example.touristagency.dto.UserDto;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.mapper.MyMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between User Entity and User Dto
 * @author djMarko97
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserViewMapper extends MyMapper<UserEntity, UserDto> {
}
