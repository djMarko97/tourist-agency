package com.example.touristagency.repository;

import com.example.touristagency.entity.DestinationEntity;
import com.example.touristagency.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    Optional<HotelEntity> findHotelByAddress(String address);

    List<HotelEntity> findHotelEntityByDestination(DestinationEntity destinationEntity);
    Optional<HotelEntity> findByImageName(String name);
}
