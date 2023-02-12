package com.mghostl.musalatest.mapper;

import com.mghostl.musalatest.dto.MedicationDTO;
import com.mghostl.musalatest.model.Medication;
import org.mapstruct.*;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

@Mapper(componentModel = "spring")
public abstract class MedicationMapper {
    @Mappings({
            @Mapping(target = "picture", ignore = true),
            @Mapping(target = "drone", ignore = true)
    })
    public abstract Medication map(MedicationDTO medication);

    @Mappings(@Mapping(target = "picture", ignore = true))
    public abstract MedicationDTO map(Medication medication);

    @AfterMapping
    public void map(MedicationDTO medicationDTO, @MappingTarget Medication medication) {
        byte[] picture = Base64.decode(medicationDTO.getPicture());
        medication.setPicture(picture);
    }

    @AfterMapping
    public void map(Medication medication, @MappingTarget MedicationDTO medicationDTO) {
        medicationDTO.setPicture(Base64.toBase64String(medication.getPicture()));
    }
}
