package com.example.touristagency.mapper;

import com.example.touristagency.dto.StateDto;
import com.example.touristagency.entity.StateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateEntity toEntity(StateDto dto);
    StateDto toDto(StateEntity entity);
}
