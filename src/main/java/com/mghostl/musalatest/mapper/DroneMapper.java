package com.mghostl.musalatest.mapper;

import com.mghostl.musalatest.dto.DroneDTO;
import com.mghostl.musalatest.model.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface DroneMapper {


    @Mappings(@Mapping(target = "medications", ignore = true))
    Drone map(DroneDTO droneDTO);

    DroneDTO map(Drone drone);
}
