package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.LoadMedicationsRequest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Model;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.repository.DroneRepository;
import com.mghostl.musalatest.service.DroneService;
import com.mghostl.musalatest.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Base64;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadControllerTest extends ControllerTest {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationService medicationService;

    @Autowired
    DroneService droneService;

    @Autowired
    MedicationMapper medicationMapper;

    @Test
    public void shouldLoadDrone() throws Exception {
        Drone drone = createDrone();
        droneRepository.save(drone);
        Set<MedicationDTO> medications = Collections.singleton(createMedicationDTO());
        Set<MedicationDTO> expectedMedications = medications.stream().peek(medication -> medication.setId(1)).collect(Collectors.toSet());
        LoadMedicationsRequest request = new LoadMedicationsRequest(medications);

        ResultActions result = mvc.perform(postContent("/load/" + drone.getSerialNumber(), request));
        checkContent(expectedMedications, result);

        assertEquals(medicationService.findAllByDroneSerialNumber(drone.getSerialNumber()), expectedMedications);

    }

    private Drone createDrone() {
        Drone drone = new Drone();
        drone.setModel(Model.HEAVY_WEIGHT);
        drone.setBatteryCapacity(100);
        drone.setSerialNumber("SerialNumber123");
        drone.setWeightLimit(499);
        drone.setState(State.IDLE);
        return drone;
    }

    private MedicationDTO createMedicationDTO() {
        MedicationDTO medicationDTO = new MedicationDTO();
        medicationDTO.setCode("CODE_123");
        medicationDTO.setName("MedicationName");
        medicationDTO.setWeight(234);
        medicationDTO.setPicture(Base64.getEncoder().encodeToString("SOME PICTURE".getBytes()));
        return medicationDTO;
    }

}
