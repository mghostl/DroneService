package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.mapper.DroneMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Model;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.repository.DroneRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createDroneDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneControllerTest extends ControllerTest {


    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @AfterAll
    public void clean() {
    }

    @Test
    public void shouldRegisterDrone() throws Exception {
        DroneDTO drone = createDroneDTO();
        DroneDTO expectedResult = new DroneDTO(drone.getSerialNumber(), drone.getModel(), drone.getWeightLimit(), drone.getBatteryCapacity(), State.IDLE);

        ResultActions resultActions = mvc.perform(postContent("/drones", drone));

        checkContent(expectedResult, resultActions);
        assertEquals(droneMapper.map(droneRepository.findById(drone.getSerialNumber()).orElse(null)), expectedResult);
    }

    @Test
    public void shouldReturnBatteryCapacityForDrone() throws Exception {
        Drone drone = createDrone();
        droneRepository.save(drone);

        ResultActions resultActions = mvc.perform(get("/drones/" + drone.getSerialNumber() + "/battery"));

        checkContent(drone.getBatteryCapacity(), resultActions);
    }

    @ParameterizedTest
    @MethodSource("invalidDronesData")
    public void shouldValidateDataForDroneRegistration(DroneDTO droneDTO) throws Exception {
        mvc.perform(postContent("/drones", droneDTO))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotPermitRegisteringOneDroneTwice() throws Exception {
        DroneDTO droneDTO = createDroneDTO();

        mvc.perform(postContent("/drones", droneDTO));
        mvc.perform(postContent("/drones", droneDTO))
                .andExpect(status().isConflict());
    }

    private static Stream<DroneDTO> invalidDronesData() {
        return Stream.of(
                new DroneDTO("", Model.CRUISER_WEIGHT, 1, 90, null),
                new DroneDTO(null, Model.CRUISER_WEIGHT, 1, 90, null),
                new DroneDTO("i".repeat(255), Model.CRUISER_WEIGHT, 1, 90, null),
                new DroneDTO("serialNumber", null, 1, 90, null),
                new DroneDTO("serialNumber", Model.HEAVY_WEIGHT, 501, 90, null),
                new DroneDTO("serialNumber", Model.HEAVY_WEIGHT, -1, 90, null),
                new DroneDTO("serialNumber", Model.HEAVY_WEIGHT, 499, -1, null),
                new DroneDTO("serialNumber", Model.HEAVY_WEIGHT, 499, 101, null)
        );
    }
}
