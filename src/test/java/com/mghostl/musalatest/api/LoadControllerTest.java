package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.dto.LoadMedicationsRequest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.DroneMapper;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.repository.DroneRepository;
import com.mghostl.musalatest.service.DroneService;
import com.mghostl.musalatest.service.LoadDroneService;
import com.mghostl.musalatest.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createMedicationDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoadControllerTest extends ControllerTest {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationService medicationService;

    @Autowired
    DroneService droneService;

    @Autowired
    MedicationMapper medicationMapper;

    @Autowired
    DroneMapper droneMapper;

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

    @Test
    public void shouldGetAvailableDronesForLoading() throws Exception {
        Drone drone = createDrone();
        droneRepository.save(drone);
        Set<DroneDTO> expectedDrones = Collections.singleton(droneMapper.map(drone));

        ResultActions result = mvc.perform(get("/load/drones"));

        checkContent(expectedDrones, result);
    }

    @ParameterizedTest
    @EnumSource(value = State.class, mode = EnumSource.Mode.EXCLUDE, names = {"IDLE", "LOADING"})
    public void shouldNotLoadDronesInTheUnappropriatedState(State state) throws Exception {
        Drone drone = createDrone();
        drone.setState(state);
        droneRepository.save(drone);
        Set<MedicationDTO> medications = Collections.singleton(createMedicationDTO());

        mvc.perform(postContent("/load/" + drone.getSerialNumber(), new LoadMedicationsRequest(medications)))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldNotLoadDronesTooMuch() throws Exception {
        Drone drone = createDrone();
        droneRepository.save(drone);
        MedicationDTO medicationDTO = createMedicationDTO();
        medicationDTO.setWeight(LoadDroneService.MAX_LOAD_WEIGHT * 2);

        mvc.perform(postContent("/load/" + drone.getSerialNumber(), new LoadMedicationsRequest(Collections.singleton(medicationDTO))))
                .andExpect(status().isConflict());
    }

}
