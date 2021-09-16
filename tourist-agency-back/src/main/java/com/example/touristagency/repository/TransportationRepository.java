package com.example.touristagency.repository;

import com.example.touristagency.entity.TransportationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportationRepository extends JpaRepository<TransportationEntity, Long> {

    Optional<TransportationEntity> findById(Long id);
}
