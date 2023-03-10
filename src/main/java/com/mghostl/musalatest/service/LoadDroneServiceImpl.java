package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.exceptions.DroneIsNotReadyForLoadingException;
import com.mghostl.musalatest.exceptions.OverloadDroneException;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.model.Weightable;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class LoadDroneServiceImpl implements LoadDroneService {

    private MedicationService medicationService;
    private DroneService droneService;
    private MedicationMapper medicationMapper;

    @Transactional
    @Override
    public Set<MedicationDTO> loadDrone(String serialNumber, Set<MedicationDTO> medications) {
        Drone drone = droneService.findBySerialNumber(serialNumber);
        if (drone.getState() != State.IDLE && drone.getState() != State.LOADING) {
            log.error("Drone with serialNumber " + drone.getSerialNumber() + " in unappropriated state " + drone.getState() + " for loading");
            throw new DroneIsNotReadyForLoadingException("Drone can't be load. Its in " + drone.getState() + " state");
        }
        double addWeight = getTotalWeight(medications);
        double currentWeight = getTotalWeight(drone.getMedications());
        if (currentWeight + addWeight > drone.getWeightLimit()) {
            log.error("Drone with serialNumber " + drone.getSerialNumber() + " is overloaded for loading more");
            throw new OverloadDroneException("Too big load for drone. You can add just " + (drone.getWeightLimit() - currentWeight) + " weight");
        }
        drone.setState(State.LOADING);
        droneService.save(drone);
        return medications
                .stream()
                .map(medicationDTO -> {
                    Medication medication = medicationMapper.map(medicationDTO);
                    medication.setDrone(drone);
                    return medication;
                })
                .map(medicationService::save)
                .map(medication -> medicationMapper.map(medication))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DroneDTO> getAvailableDronesForLoading() {
        return droneService.getAvailableDronesForLoading();
    }

    private <T extends Weightable> double getTotalWeight(Set<T> weightables) {
        if (weightables.isEmpty()) return 0;
        return weightables
                .stream()
                .mapToDouble(Weightable::getWeight)
                .sum();
    }

}
