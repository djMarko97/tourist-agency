package com.example.touristagency.controller;

import com.example.touristagency.dto.ReservationDto;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.security.authorization.IsAdminUser;
import com.example.touristagency.service.impl.ReservationService;
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
@RequestMapping(path = "/reservation")
@CrossOrigin("*")
public class ReservationController implements com.example.touristagency.controller.RestController<ReservationDto, Long> {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ReservationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAll());
    }

    @GetMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<ReservationDto> dto = reservationService.findById(id);
        if(dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No reservation with id " + id);


    }

    @IsAdminUser
    @PostMapping
    @Override
    public ResponseEntity<Object> save(ReservationDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.save(dto));
        } catch (MyEntityAlreadyExists | MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path= "/{id}")
    @Override
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reservation with id : " + id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    //TODO Da li bi rezervacija mogla da se update-uje
    @Override
    public ResponseEntity<Object> update(ReservationDto dto) {
        return null;
    }
    /*
        @GetMapping(path="/myReservations/{id}")
        public ResponseEntity<List<ReservationDto>> getReservationById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationsById(id));
        }
        */
    @Override
    public ResponseEntity<Page<ReservationDto>> getByPage(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}
