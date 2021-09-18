package com.example.touristagency.controller;

import com.example.touristagency.dto.StateDto;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.security.authorization.IsAdminUser;
import com.example.touristagency.service.impl.StateService;
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
@RequestMapping(path = "/state")
@CrossOrigin("*")
public class StateController implements com.example.touristagency.controller.RestController<StateDto, Long> {

    private StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @IsAdminUser
    @GetMapping
    @Override
    public ResponseEntity<List<StateDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<StateDto> dto = stateService.findById(id);
        if (dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(dto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("State with id: " + id + " was not found!");
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(StateDto dto) {
        try {
            stateService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("State " + dto.getName() + " is created!");
        } catch (MyEntityAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("State with id " +  id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page<StateDto>> getByPage(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @PutMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> update(@RequestBody StateDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stateService.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
