package com.mghostl.musalatest.repository;

import com.mghostl.musalatest.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM medications " +
                    "WHERE drone_id = :droneId")
    Set<Medication> findAllByDroneId(String droneId);
}
