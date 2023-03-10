package com.mghostl.musalatest.api;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.service.DroneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drones")
@AllArgsConstructor
public class DroneController {

    private DroneService droneService;

    @PostMapping
    ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        return ResponseEntity.ok(droneService.save(droneDTO));
    }

    @GetMapping("{serialNumber}/battery")
    ResponseEntity<Integer> getDroneBattery(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.findBySerialNumber(serialNumber).getBatteryCapacity());
    }

}
