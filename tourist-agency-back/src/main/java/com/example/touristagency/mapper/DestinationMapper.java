package com.example.touristagency.mapper;

import com.example.touristagency.dto.DestinationDto;
import com.example.touristagency.entity.DestinationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StateMapper.class)
public interface DestinationMapper extends MyMapper<DestinationEntity, DestinationDto>{
}
