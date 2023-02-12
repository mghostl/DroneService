package com.mghostl.musalatest.repository;


import com.mghostl.musalatest.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
}
