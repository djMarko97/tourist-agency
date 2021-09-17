package com.example.touristagency.service.impl;

import com.example.touristagency.dto.HotelDto;
import com.example.touristagency.entity.DestinationEntity;
import com.example.touristagency.entity.HotelEntity;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.mapper.DestinationMapper;
import com.example.touristagency.mapper.HotelMapper;
import com.example.touristagency.repository.DestinationRepository;
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
public class HotelService implements MyService<HotelDto, Long>{
    private HotelMapper mapper;
    private HotelRepository repository;
    private RoomRepository roomRepository;
    private DestinationMapper destinationMapper;

    private final DestinationRepository destinationRepository;

    @Autowired
    public HotelService(HotelMapper mapper, HotelRepository repository, RoomRepository roomRepository,
                        DestinationRepository destinationRepository,
                        DestinationMapper destinationMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.roomRepository = roomRepository;
        this.destinationRepository = destinationRepository;
        this.destinationMapper = destinationMapper;
    }

    @Override
    public Optional<HotelDto> findById(Long id) {
        Optional<HotelEntity> entity = repository.findById(id);
        if(entity.isPresent()) {
            HotelDto dto = mapper.toDto(entity.get());
            byte[] image = dto.getImage();
            dto.setImage(HotelController.decompressBytes(image));
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public List<HotelDto> getAll() {
        List<HotelEntity> entities = repository.findAll();
        return entities.stream().map(en -> {
            return mapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public HotelDto save(HotelDto dto) throws MyEntityAlreadyExists {
		/*Optional<HotelEntity> hotelEntity = repository.findHotelByAddress(dto.getAddress());
		if(hotelEntity.isPresent()) {
			throw new MyEntityAlreadyExists("Hotel " +
					" at address " + hotelEntity.get().getAddress() +
					" already exists!");
		}
		else {*/
        HotelEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);

    }

    @Override
    public void delete(Long id) throws MyEntityDoesntExist {
        Optional<HotelEntity> entity = repository.findById(id);
        if(entity.isPresent()) {
            repository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Hotel with id: " + id + " doesn't exist!");

    }

    @Override
    public Optional<HotelDto> update(HotelDto dto) throws MyEntityDoesntExist {

        HotelEntity entity = repository.findById(dto.getId()).orElseThrow(
                ()-> new MyEntityDoesntExist("Hotel with id: " + dto.getId() + " doesn't exist!"));

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setRating(dto.getRating());
        entity.setDestination(destinationMapper.toEntity(dto.getDestination()));

        repository.save(entity);
        dto.setImage(entity.getImage());
        dto.setImageName(entity.getImageName());
        dto.setImageType(entity.getImageType());
        return Optional.of(dto);

		/*Optional<HotelEntity> entity = repository.findById(dto.getId());
		if(entity.isPresent()) {
			repository.save(mapper.toEntity(dto));
			return Optional.of(dto);
		}
		else {
			throw new MyEntityDoesntExist("Hotel with id: " + dto.getId() +  " doesn't exist!");
		}*/
    }

    @Override
    public Page<HotelDto> findByPage(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<HotelDto> findHotelEntityByDestinationId(Long id) {
        DestinationEntity destinationEntity = this.destinationRepository.findById(id).orElse(null);
        if(destinationEntity == null) {
            throw new EntityNotFoundException("Destination with that id can not be found");
        }
        List<HotelEntity> hotels = this.repository.findHotelEntityByDestination(destinationEntity);
        return hotels.stream().map((hotelEntity -> {
            return this.mapper.toDto(hotelEntity);
        })).collect(Collectors.toList());
    }
}
