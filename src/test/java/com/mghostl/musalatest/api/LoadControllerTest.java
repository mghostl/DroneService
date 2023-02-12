package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.LoadMedicationsRequest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.repository.DroneRepository;
import com.mghostl.musalatest.service.DroneService;
import com.mghostl.musalatest.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createMedicationDTO;
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

}
