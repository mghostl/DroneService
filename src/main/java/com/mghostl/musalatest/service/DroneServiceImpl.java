package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.exceptions.DroneAlreadyExistsException;
import com.mghostl.musalatest.exceptions.DroneNotExistsException;
import com.mghostl.musalatest.mapper.DroneMapper;
import com.mghostl.musalatest.model.Drone;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.repository.DroneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    private final DroneMapper droneMapper;

    @Override
    @Transactional
    public DroneDTO save(DroneDTO droneDTO) {
        droneRepository.findById(droneDTO.getSerialNumber()).ifPresent(drone -> {
            throw new DroneAlreadyExistsException("Drone with serial number " + drone.getSerialNumber() + " already exists");
        });
        Drone drone = droneRepository.save(droneMapper.map(droneDTO));
        drone.setState(State.IDLE);
        return droneMapper.map(drone);
    }

    @Transactional
    @Override
    public void save(Drone drone) {
        droneRepository.save(drone);
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        return droneRepository.findById(serialNumber)
                .orElseThrow(() -> new DroneNotExistsException("Drone with serial number " + serialNumber + " doesn't exists"));
    }
}
