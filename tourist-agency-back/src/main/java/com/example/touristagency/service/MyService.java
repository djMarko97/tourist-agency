package com.example.touristagency.service;

import com.example.touristagency.dto.MyDto;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MyService<DTO extends MyDto, ID> {

    // CRUD operacije
    Optional<DTO> findById(ID id);

    List<DTO> getAll();

    DTO save(DTO dto) throws MyEntityAlreadyExists, MyEntityDoesntExist;

    void delete(ID id) throws MyEntityDoesntExist;

    Optional<DTO> update(DTO dto) throws MyEntityDoesntExist, MyEntityAlreadyExists;

    Page<DTO> findByPage(Pageable pageable);
}
