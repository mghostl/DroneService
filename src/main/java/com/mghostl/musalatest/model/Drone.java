package com.mghostl.musalatest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "drones")
public class Drone {
    @Id
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Model model;

    private int weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private State state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private Set<Medication> medications;
}
