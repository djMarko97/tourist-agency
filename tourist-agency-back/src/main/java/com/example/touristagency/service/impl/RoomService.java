package com.example.touristagency.service.impl;

import com.example.touristagency.dto.HotelDto;
import com.example.touristagency.dto.RoomDto;
import com.example.touristagency.entity.HotelEntity;
import com.example.touristagency.entity.RoomEntity;
import com.example.touristagency.entity.RoomIdentity;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.mapper.HotelMapper;
import com.example.touristagency.mapper.RoomMapper;
import com.example.touristagency.repository.HotelRepository;
import com.example.touristagency.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService implements MyService<RoomDto, RoomIdentity>{
    private RoomMapper mapper;
    private RoomRepository repository;
    private HotelRepository hotelRepository;
    private HotelMapper hotelMapper;
    private RoomMapper roomMapper;

    @Autowired
    public RoomService(RoomMapper mapper, RoomRepository repository,
                       HotelRepository hotelRepository, HotelMapper hotelMapper, RoomMapper roomMapper){
        this.mapper = mapper;
        this.repository = repository;
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.roomMapper = roomMapper;
    }

    @Override
    public Optional<RoomDto> findById(RoomIdentity id) {
        Optional<RoomEntity> entity = repository.findById(id);
        if(entity.isPresent()) {
            return Optional.of(mapper.toDto(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<RoomDto> getAll(){
        List<RoomEntity> entities = repository.findAll();
        return entities.stream().map(en -> {
            return mapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public RoomDto save(RoomDto dto) throws MyEntityAlreadyExists, MyEntityDoesntExist{
        Optional<RoomEntity> entity = repository.findById(dto.getId());
        if(entity.isPresent()) {
            throw new MyEntityAlreadyExists("Room " + entity.get().getId() +
                    " already exists in the system!");
        }
        hotelRepository.findById(dto.getHotel().getId()).orElseThrow(
                () -> new MyEntityDoesntExist("Hotel with id: " + dto.getHotel().getId() + "doesn't exist!"));

        RoomEntity roomToSave = this.roomMapper.toEntity(dto);
        return this.roomMapper.toDto(this.repository.save(roomToSave));
    }

    @Override
    public void delete(RoomIdentity id) throws MyEntityDoesntExist {
        Optional<RoomEntity> entity = repository.findById(id);
        if(entity.isPresent()) {
            repository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Room with id: " + id.getRoom_number() + " doesn't exist!");

    }

    @Override
    public Optional<RoomDto> update(RoomDto dto) throws MyEntityDoesntExist {
        Optional<RoomEntity> entity = repository.findById(dto.getId());
        if(entity.isPresent()) {
            HotelEntity hotelEntity = hotelRepository.findById(dto.getHotel().getId()).orElseThrow(
                    () -> new MyEntityDoesntExist("Hotel with id: " + dto.getHotel().getId() + "doesn't exist!"));
            HotelDto hotelDto = hotelMapper.toDto(hotelEntity);
            dto.getHotel().setImage(hotelDto.getImage());
            dto.getHotel().setImageName(hotelDto.getImageName());
            dto.getHotel().setImageType(hotelDto.getImageType());
            repository.save(mapper.toEntity(dto));
            return Optional.of(dto);
        }
        else {
            throw new MyEntityDoesntExist("Room " + dto.getId()  + " doesn't exist!");
        }
    }

    public List<RoomDto> findRoomsByHotelId(Long id) {
        // Bacamo spring exception jer spring to zna da handluje, metoda u kontroleru je overajdovana i onda morali bi svuda da menjamo
        this.hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with id: " + id + " does not exist"));

        return repository.findRoomsByHotelId(id).stream().map(this.mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<RoomDto> findByPage(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}





