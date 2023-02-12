package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.service.DroneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.Set;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createMedication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class MedicationControllerTest extends ControllerTest {

    @Autowired
    DroneService droneService;

    @Autowired
    MedicationMapper medicationMapper;

    @Test
    public void shouldReturnMedicationsForDrone() throws Exception {
        Drone drone = createDrone();
        Medication medication = createMedication();
        medication.setDrone(drone);
        drone.setMedications(Collections.singleton(medication));
        MedicationDTO expectedMedication = medicationMapper.map(medication);
        Set<MedicationDTO> expectedMedications = Collections.singleton(expectedMedication);
        Drone savedDrone = droneService.save(drone);
        expectedMedication.setId(savedDrone.getMedications().stream().toList().get(0).getId());

        ResultActions resultActions = mvc.perform(get("/medications/" + drone.getSerialNumber()));

        checkContent(expectedMedications, resultActions);
    }
}
