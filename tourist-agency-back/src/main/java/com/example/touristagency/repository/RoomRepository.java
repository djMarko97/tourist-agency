package com.example.touristagency.repository;

import com.example.touristagency.entity.RoomEntity;
import com.example.touristagency.entity.RoomIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, RoomIdentity> {
    List<RoomEntity> findRoomsByHotelId(Long id);
}
