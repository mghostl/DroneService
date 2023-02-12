package com.mghostl.musalatest.api;

import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("medications")
@RestController
@AllArgsConstructor
public class MedicationController {

    private MedicationService medicationService;

    @GetMapping("{serialNumber}")
    ResponseEntity<Set<MedicationDTO>> getMedicationsForDrone(@PathVariable String serialNumber) {
        return ResponseEntity.ok(medicationService.findAllByDroneSerialNumber(serialNumber));
    }
}
