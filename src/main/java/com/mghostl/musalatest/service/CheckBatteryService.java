package com.mghostl.musalatest.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface CheckBatteryService {
    void checkDronesBattery();
}
