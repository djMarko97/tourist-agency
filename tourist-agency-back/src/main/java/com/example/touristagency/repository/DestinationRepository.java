package com.example.touristagency.repository;

import com.example.touristagency.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
    @Override
    Optional<DestinationEntity> findById(Long id);
    DestinationEntity findDestinationById(Long id);
}
