package com.example.touristagency.controller;

import com.example.touristagency.dto.DestinationDto;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.security.authorization.IsAdminUser;
import com.example.touristagency.service.impl.DestinationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/destination")
@CrossOrigin("*")
public class DestinationController implements com.example.touristagency.controller.RestController<DestinationDto, Long> {

    private DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @IsAdminUser
    @GetMapping
    @Override
    public ResponseEntity<List<DestinationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(destinationService.getAll());
    }

    @GetMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> findById(@PathVariable Long ID) {
        Optional<DestinationDto> dto = destinationService.findById(ID);
        if (dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(dto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Destination with id: " + ID + " was not found!");
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(DestinationDto dto) {
        try {
            destinationService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Destination " + dto.getName() + " is created!");
        } catch (MyEntityAlreadyExists | MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        try {
            destinationService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Destination with id " +  id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page<DestinationDto>> getByPage(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @PutMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> update(@RequestBody DestinationDto dto) {
        try {
            destinationService.update(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Destination : "  + dto.getName()+ " is updated!");
        } catch (MyEntityAlreadyExists | MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
