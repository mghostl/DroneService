package com.mghostl.musalatest.dto;

import com.mghostl.musalatest.model.Weightable;
import com.mghostl.musalatest.validation.ValidCode;
import com.mghostl.musalatest.validation.ValidName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Getter
@Setter
@Validated
@EqualsAndHashCode
public class MedicationDTO implements Weightable {
    private int id;
    @NotBlank
    @NotNull
    @ValidName
    private String name;
    @Range(min = 0)
    private double weight;
    @NotBlank
    @NotNull
    @ValidCode
    private String code;
    @NotNull
    private String picture;
}
