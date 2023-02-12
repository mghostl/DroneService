package com.mghostl.musalatest.dto;

import com.mghostl.musalatest.model.Model;
import com.mghostl.musalatest.model.State;
import com.mghostl.musalatest.service.LoadDroneService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode
public class DroneDTO {

    @NotBlank
    @Length(max = 100)
    @NotNull
    private String serialNumber;

    @NotNull
    private Model model;

    @Range(min = 0, max = LoadDroneService.MAX_LOAD_WEIGHT)
    private int weightLimit;

    @Range(min = 0, max = 100)
    private int batteryCapacity;
    private State state;
}
