package com.mghostl.musalatest.repository;


import com.mghostl.musalatest.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DroneRepository extends JpaRepository<Drone, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM drones " +
                    "WHERE state = 'IDLE' OR state = 'LOADING'"
    )
    Set<Drone> getAvailableDronesForLoading();
}
