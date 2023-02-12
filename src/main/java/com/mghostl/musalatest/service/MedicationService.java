package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.model.Medication;

import java.util.Set;

public interface MedicationService {
    Medication save(Medication medication);

    Set<MedicationDTO> findAllByDroneSerialNumber(String serialNumber);

}
