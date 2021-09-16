package com.example.touristagency.mapper;

import com.example.touristagency.dto.MyDto;
import com.example.touristagency.entity.MyEntity;

import java.util.List;

public interface MyMapper<Entity extends MyEntity, Dto extends MyDto> {
    public Entity toEntity(Dto dto);
    public Dto toDto(Entity entity);

    /**
     * Maps list of DTOs to list of Entites
     * @param dtoList DTO list
     * @return list of entities
     */
    List<Entity> toEntity(List<Dto> dtoList);

    /**
     * Maps list of Entities to list of DTOs
     * @param entityList entity list
     * @return list of DTOs
     */
    List<Dto> toDto(List<Entity> entityList);
}
