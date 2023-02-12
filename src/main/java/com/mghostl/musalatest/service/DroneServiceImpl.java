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

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    private final DroneMapper droneMapper;

    @Override
    @Transactional
    public DroneDTO save(DroneDTO droneDTO) {
        if (droneRepository.existsById(droneDTO.getSerialNumber())) {
            throw new DroneAlreadyExistsException("Drone with serial number " + droneDTO.getSerialNumber() + " already exists");
        }

        Drone drone = droneMapper.map(droneDTO);
        drone.setState(State.IDLE);
        drone = droneRepository.save(drone);
        return droneMapper.map(drone);
    }

    @Transactional
    @Override
    public Drone save(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        return droneRepository.findById(serialNumber)
                .orElseThrow(() -> new DroneNotExistsException("Drone with serial number " + serialNumber + " doesn't exists"));
    }

    @Transactional
    @Override
    public Set<DroneDTO> getAvailableDronesForLoading() {
        return droneRepository.getAvailableDronesForLoading()
                .stream().map(droneMapper::map)
                .collect(Collectors.toSet());
    }
}
