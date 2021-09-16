package com.example.touristagency.mapper;

import com.example.touristagency.dto.ReservationDto;
import com.example.touristagency.entity.ReservationEntity;
import com.example.touristagency.mapper.authority.UserCreateMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserCreateMapper.class)
public interface ReservationMapper{
    ReservationEntity toEntity(ReservationDto dto);
    ReservationDto toDto(ReservationEntity entity);
}
