package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.model.Drone;

public interface DroneService {
    DroneDTO save(DroneDTO droneDTO);

    Drone save(Drone drone);

    Drone findBySerialNumber(String serialNumber);

}
