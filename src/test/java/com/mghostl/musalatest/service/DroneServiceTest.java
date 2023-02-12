package com.mghostl.musalatest.service;

import com.mghostl.musalatest.base.MockTest;
import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.exceptions.DroneAlreadyExistsException;
import com.mghostl.musalatest.exceptions.DroneNotExistsException;
import com.mghostl.musalatest.mapper.DroneMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createDroneDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DroneServiceTest implements MockTest {

    @MockBean
    DroneRepository droneRepository;

    @MockBean
    DroneMapper droneMapper;

    @Autowired
    DroneService droneService;

    @Test
    public void shouldSaveDrone() {
        DroneDTO droneDTO = createDroneDTO();
        Drone drone = createDrone();
        when(droneMapper.map(droneDTO)).thenReturn(drone);
        when(droneRepository.save(drone)).thenReturn(drone);
        when(droneRepository.existsById(droneDTO.getSerialNumber())).thenReturn(false);
        when(droneMapper.map(drone)).thenReturn(droneDTO);

        DroneDTO actualDrone = droneService.save(droneDTO);

        verify(droneRepository).save(drone);
        assertEquals(droneDTO, actualDrone);
    }

    @Test
    public void shouldFindBySerialNumber() {
        Drone drone = createDrone();
        when(droneRepository.findById(drone.getSerialNumber())).thenReturn(Optional.of(drone));

        Drone actualDrone = droneService.findBySerialNumber(drone.getSerialNumber());

        assertEquals(actualDrone, drone);
    }

    @Test
    public void shouldThrowExceptionIfDroneToSaveExists() {
        DroneDTO droneDTO = createDroneDTO();
        Drone drone = createDrone();

        when(droneMapper.map(droneDTO)).thenReturn(drone);
        when(droneRepository.existsById(drone.getSerialNumber())).thenReturn(true);

        assertThrows(DroneAlreadyExistsException.class, () -> droneService.save(droneDTO));
    }

    @Test
    public void shouldThrowExceptionIfDroneBySerialNumberDoesNotExist() {
        String serialNumber = "SerialNumber";
        when(droneRepository.findById(serialNumber)).thenReturn(Optional.empty());

        assertThrows(DroneNotExistsException.class, () -> droneService.findBySerialNumber(serialNumber));
    }
}
