package com.example.touristagency.controller;


import com.example.touristagency.dto.HotelDto;
import com.example.touristagency.dto.MyDto;
import com.example.touristagency.entity.DestinationEntity;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.mapper.DestinationMapper;
import com.example.touristagency.repository.DestinationRepository;
import com.example.touristagency.security.authorization.IsAdminUser;
import com.example.touristagency.service.impl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(path = "/hotel")
@CrossOrigin("*")
public class HotelController implements RestHotelController{

    private HotelService service;
    //private DestinationService destinationService;
    private DestinationRepository destinationRepo;
    private DestinationMapper destinationMapper;

    @Autowired
    public HotelController(HotelService service, DestinationRepository destinationRepo, DestinationMapper destinationMapper) {
        this.service = service;
        this.destinationRepo = destinationRepo;
        this.destinationMapper = destinationMapper;
    }

    @IsAdminUser
    @GetMapping
    @Override
    public ResponseEntity<List> getAll() {
        List<HotelDto> hotels = service.getAll();
        for (HotelDto hotel : hotels) {
            byte[] image = hotel.getImage();
            hotel.setImage(decompressBytes(image));
        }
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }

    @IsAdminUser
    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(Long ID) {
        Optional<HotelDto> dto = service.findById(ID);

        if (dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(dto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hotel with id: " + ID + " was not found!");
    }

    @IsAdminUser
    @PostMapping
    @Override
    public ResponseEntity<Object> save(String name, String address, Integer rating,
                                       Long destination_id, MultipartFile imageFile) throws IOException {

        HotelDto dto = new HotelDto();
        dto.setName(name);
        dto.setAddress(address);
        dto.setRating(rating);

        DestinationEntity destination = destinationRepo.findDestinationById(destination_id);

        dto.setDestination(destinationMapper.toDto(destination));
        dto.setImageName(imageFile.getOriginalFilename());
        dto.setImageType(imageFile.getContentType());
        dto.setImage(compressBytes(imageFile.getBytes()));
        //dto.setExtension(extension);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
        } catch (MyEntityAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @IsAdminUser
    @DeleteMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> deleteById(Long ID) {
        try {
            service.delete(ID);
            return ResponseEntity.status(HttpStatus.OK).body("Hotel with id " +  ID + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @IsAdminUser
    @PutMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> update(HotelDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page> getByPage(Pageable pageable) {
        return null;
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            } outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }

        return outputStream.toByteArray();
    }

    @GetMapping("destination")
    public ResponseEntity<List<HotelDto>> getAllByDestination(@RequestParam Long destinationId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findHotelEntityByDestinationId(destinationId));
    }
}
