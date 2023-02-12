package com.mghostl.musalatest.data;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.model.Model;
import com.mghostl.musalatest.model.State;

import java.util.Base64;
import java.util.UUID;

public class TestDataUtils {
    public static DroneDTO createDroneDTO() {
        DroneDTO drone = new DroneDTO();
        drone.setModel(Model.HEAVY_WEIGHT);
        drone.setBatteryCapacity(100);
        drone.setSerialNumber(UUID.randomUUID().toString());
        drone.setWeightLimit(499);
        return drone;
    }

    public static Drone createDrone() {
        Drone drone = new Drone();
        drone.setModel(Model.HEAVY_WEIGHT);
        drone.setBatteryCapacity(100);
        drone.setState(State.IDLE);
        drone.setSerialNumber(UUID.randomUUID().toString());
        drone.setWeightLimit(499);
        return drone;
    }

    public static MedicationDTO createMedicationDTO() {
        MedicationDTO medicationDTO = new MedicationDTO();
        medicationDTO.setCode("CODE_123");
        medicationDTO.setName("MedicationName");
        medicationDTO.setWeight(234);
        medicationDTO.setPicture(Base64.getEncoder().encodeToString("SOME PICTURE".getBytes()));
        return medicationDTO;
    }
    public static Medication createMedication() {
        Medication medication = new Medication();
        medication.setCode("CODE_123");
        medication.setName("MedicationName");
        medication.setWeight(234);
        medication.setPicture("SOME PICTURE".getBytes());
        return medication;
    }
}
