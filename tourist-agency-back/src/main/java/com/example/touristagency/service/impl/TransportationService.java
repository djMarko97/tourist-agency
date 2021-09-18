package com.example.touristagency.service.impl;

import com.example.touristagency.dto.TransportationDto;
import com.example.touristagency.entity.DestinationEntity;
import com.example.touristagency.entity.TransportationEntity;
import com.example.touristagency.exception.MyEntityAlreadyExists;
import com.example.touristagency.exception.MyEntityDoesntExist;
import com.example.touristagency.mapper.DestinationMapper;
import com.example.touristagency.mapper.TransportationMapper;
import com.example.touristagency.repository.DestinationRepository;
import com.example.touristagency.repository.TransportationRepository;
import com.example.touristagency.service.MyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransportationService implements MyService<TransportationDto, Long> {

    /** Transportation Repository **/
    private final TransportationRepository transportationRepository;

    /** Destination entity **/
    private final DestinationRepository destinationRepository;

    /** Transportation Mapper **/
    private final TransportationMapper transportationMapper;

    /** Destination Mapper **/
    private final DestinationMapper destinationMapper;

    public TransportationService(TransportationRepository transportationRepository, DestinationRepository destinationRepository, TransportationMapper transportationMapper, DestinationMapper destinationMapper) {
        this.transportationRepository = transportationRepository;
        this.destinationRepository = destinationRepository;
        this.transportationMapper = transportationMapper;
        this.destinationMapper = destinationMapper;
    }

    @Override
    public Optional<TransportationDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<TransportationDto> getAll() {
        List<TransportationEntity> transportationEntities = this.transportationRepository.findAll();
        return transportationEntities.stream().map(this.transportationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TransportationDto save(TransportationDto dto) throws MyEntityAlreadyExists, MyEntityDoesntExist {
        DestinationEntity startDestination = this.destinationRepository.findById(dto.getStart().getId()).orElse(null);
        DestinationEntity endDestination = this.destinationRepository.findById(dto.getEnd().getId()).orElse(null);

        if(startDestination == null || endDestination == null) {
            throw  new MyEntityDoesntExist("Given destinations can not be found");
        } else {
            dto.setStart(this.destinationMapper.toDto(startDestination));
            dto.setEnd(this.destinationMapper.toDto(endDestination));
            TransportationEntity transportationEntity = this.transportationMapper.toEntity(dto);
            this.transportationRepository.save(this.transportationMapper.toEntity(dto));
            return dto;
        }
    }

    @Override
    public void delete(Long aLong) throws MyEntityDoesntExist {

    }

    @Override
    public Optional<TransportationDto> update(TransportationDto dto) throws MyEntityDoesntExist, MyEntityAlreadyExists {
        return Optional.empty();
    }

    @Override
    public Page<TransportationDto> findByPage(Pageable pageable) {
        return null;
    }

}
