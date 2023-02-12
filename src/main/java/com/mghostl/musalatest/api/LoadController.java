package com.mghostl.musalatest.api;

import com.mghostl.musalatest.dto.LoadMedicationsRequest;
import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.service.LoadDroneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("load")
@RestController
@AllArgsConstructor
public class LoadController {

    private LoadDroneService loadDroneService;

    @PostMapping("{serialNumber}")
    ResponseEntity<Set<MedicationDTO>> loadDrone(@PathVariable String serialNumber, @RequestBody LoadMedicationsRequest loadMedicationsRequest) {
        return ResponseEntity.ok(loadDroneService.loadDrone(serialNumber, loadMedicationsRequest.getMedications()));
    }
}
