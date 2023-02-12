package com.mghostl.musalatest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medications")
public class Medication implements Weightable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medications_generator")
    @SequenceGenerator(name = "medications_generator", sequenceName = "medications_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private double weight;
    private String code;

    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;
}
