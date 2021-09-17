package com.example.touristagency.controller;

import com.example.touristagency.dto.MyDto;
import com.example.touristagency.entity.RoomIdentity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface RestRoomController<DTO extends MyDto, ID> {

    @ResponseBody
    ResponseEntity<List<DTO>> getAll();

    @ResponseBody ResponseEntity<Object> findById(@PathVariable RoomIdentity ID);

    @ResponseBody ResponseEntity<List<DTO>> findRoomsByHotelId(@PathVariable Long ID);

    @ResponseBody ResponseEntity<Object> save(@RequestBody DTO dto);

    @ResponseBody ResponseEntity<Object> deleteById(@RequestBody RoomIdentity ID);

    @ResponseBody ResponseEntity<Object> update(@RequestBody DTO dto);

    @ResponseBody ResponseEntity<Page<DTO>> getByPage(Pageable pageable);
}
