package com.example.touristagency.mapper;

import com.example.touristagency.dto.TransportationDto;
import com.example.touristagency.entity.TransportationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DestinationMapper.class)
public interface TransportationMapper {
    TransportationDto toDto(TransportationEntity entity);
    TransportationEntity toEntity(TransportationDto dto);
}
