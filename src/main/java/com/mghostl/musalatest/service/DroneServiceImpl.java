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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    private final DroneMapper droneMapper;

    @Override
    @Transactional
    public DroneDTO save(DroneDTO droneDTO) {
        if (droneRepository.existsById(droneDTO.getSerialNumber())) {
            log.error("Drone with serial number " + droneDTO.getSerialNumber() + " already exists. Can't register it");
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
                .orElseThrow(() -> {
                    log.error("Drone with serialNumber " + serialNumber + " does not exist");
                    return new DroneNotExistsException("Drone with serial number " + serialNumber + " doesn't exists");
                });
    }

    @Transactional
    @Override
    public Set<DroneDTO> getAvailableDronesForLoading() {
        return droneRepository.getAvailableDronesForLoading()
                .stream().map(droneMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }
}
