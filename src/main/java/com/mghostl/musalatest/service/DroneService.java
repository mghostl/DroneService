package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.model.Drone;

import java.util.List;
import java.util.Set;

public interface DroneService {
    DroneDTO save(DroneDTO droneDTO);

    Drone save(Drone drone);

    Drone findBySerialNumber(String serialNumber);

    Set<DroneDTO> getAvailableDronesForLoading();

    List<Drone> getAllDrones();

}
