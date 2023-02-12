package com.mghostl.musalatest.api;

import com.mghostl.musalatest.base.ControllerTest;
import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.mapper.DroneMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static com.mghostl.musalatest.data.TestDataUtils.createDroneDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class DroneControllerTest extends ControllerTest {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

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
}
