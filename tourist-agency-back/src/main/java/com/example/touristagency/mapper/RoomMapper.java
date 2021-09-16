package com.example.touristagency.mapper;

import com.example.touristagency.dto.RoomDto;
import com.example.touristagency.entity.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = HotelMapper.class)
public interface RoomMapper extends MyMapper<RoomEntity, RoomDto> {

}
