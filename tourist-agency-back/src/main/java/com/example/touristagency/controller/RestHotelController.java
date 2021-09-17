package com.example.touristagency.controller;

import com.example.touristagency.dto.HotelDto;
import com.example.touristagency.dto.MyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestHotelController<DTO extends MyDto, ID> {

    @ResponseBody
    ResponseEntity<List<DTO>> getAll();

    @ResponseBody ResponseEntity<Object> findById(@PathVariable Long ID);

    @ResponseBody ResponseEntity<Object> save(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("rating") Integer rating,
            @RequestParam("destination_id") Long destination_id,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException;

    @ResponseBody ResponseEntity<Object> deleteById(@PathVariable Long ID);

    @ResponseBody ResponseEntity<Object> update(@RequestBody HotelDto dto);

    @ResponseBody ResponseEntity<Page<DTO>> getByPage(Pageable pageable);
}
