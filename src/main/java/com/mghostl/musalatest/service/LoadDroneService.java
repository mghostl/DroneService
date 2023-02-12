package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.dto.MedicationDTO;

import java.util.Set;

public interface LoadDroneService {
    int MAX_LOAD_WEIGHT = 500;
    Set<MedicationDTO> loadDrone(String serialNumber, Set<MedicationDTO> medications);

    Set<DroneDTO> getAvailableDronesForLoading();
}
