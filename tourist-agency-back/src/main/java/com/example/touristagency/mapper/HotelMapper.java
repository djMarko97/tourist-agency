package com.example.touristagency.mapper;

import com.example.touristagency.dto.HotelDto;
import com.example.touristagency.entity.HotelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface HotelMapper extends MyMapper<HotelEntity, HotelDto> {
}
