package com.mghostl.musalatest.service;

import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.mapper.MedicationMapper;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService{

    private MedicationRepository medicationRepository;

    private MedicationMapper medicationMapper;

    @Override
    @Transactional
    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public Set<MedicationDTO> findAllByDroneSerialNumber(String serialNumber) {
        return medicationRepository.findAllByDroneId(serialNumber)
                .stream()
                .map(medicationMapper::map)
                .collect(Collectors.toSet());
    }
}
