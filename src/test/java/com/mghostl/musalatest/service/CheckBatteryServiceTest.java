package com.mghostl.musalatest.service;

import com.mghostl.musalatest.base.MockTest;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.mghostl.musalatest.data.TestDataUtils.createDrone;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CheckBatteryServiceTest implements MockTest {
    @Autowired
    CheckBatteryService checkBatteryService;

    @MockBean
    DroneService droneService;

    @Test
    public void shouldPreventLoadingStateForDronesWithBatteryLessThan25() {
        Drone drone = createDrone();
        drone.setState(State.LOADING);
        drone.setBatteryCapacity(24);
        when(droneService.getAllDrones()).thenReturn(List.of(drone));

        checkBatteryService.checkDronesBattery();

        assertEquals(drone.getState(), State.IDLE);
    }
}
