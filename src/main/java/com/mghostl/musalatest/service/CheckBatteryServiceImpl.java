package com.mghostl.musalatest.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CheckBatteryServiceImpl implements CheckBatteryService {

    DroneService droneService;

    @Scheduled(cron = "1 * * * * *")
    @Override
    public void checkDronesBattery() {
        droneService.getAllDrones()
                .forEach(drone -> log.info("Battery for drone with serialNumber "
                        + drone.getSerialNumber() + " is " + drone.getBatteryCapacity()));
    }
}
