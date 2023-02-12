package com.mghostl.musalatest.service;

import com.mghostl.musalatest.base.MockTest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.model.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Set;

import static com.mghostl.musalatest.data.TestDataUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoadDroneServiceTest implements MockTest {

    @MockBean
    MedicationService medicationService;

    @MockBean
    DroneService droneService;

    @MockBean
    MedicationMapper medicationMapper;

    @Autowired
    LoadDroneService loadDroneService;

    @Test
    public void shouldLoadDroneWithMedications() {
        MedicationDTO medicationDTO = createMedicationDTO();
        Set<MedicationDTO> medications = Collections.singleton(medicationDTO);
        Medication medication = createMedication();
        String serialNumber = "serialNumber";
        Drone drone = createDrone();
        drone.setState(State.IDLE);
        drone.setMedications(Collections.singleton(medication));
        when(droneService.findBySerialNumber(serialNumber)).thenReturn(drone);
        when(medicationMapper.map(medicationDTO)).thenReturn(medication);
        when(medicationService.save(medication)).thenReturn(medication);
        when(medicationMapper.map(medication)).thenReturn(medicationDTO);

        Set<MedicationDTO> actualMedications = loadDroneService.loadDrone(serialNumber, medications);

        assertEquals(actualMedications, medications);
        verify(droneService).save(drone);
        verify(medicationService).save(medication);
    }

}
