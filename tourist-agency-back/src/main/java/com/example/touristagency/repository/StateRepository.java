package com.example.touristagency.repository;

import com.example.touristagency.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

}
