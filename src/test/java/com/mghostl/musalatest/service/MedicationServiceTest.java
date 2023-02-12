package com.mghostl.musalatest.service;

import com.mghostl.musalatest.base.MockTest;
import com.mghostl.musalatest.model.Medication;
import com.mghostl.musalatest.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Set;

import static com.mghostl.musalatest.data.TestDataUtils.createMedication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MedicationServiceTest implements MockTest {
    @Autowired
    MedicationService medicationService;

    @MockBean
    MedicationRepository medicationRepository;

    @Test
    public void shouldSaveMedication() {
        Medication medication = createMedication();
        when(medicationRepository.save(medication)).thenReturn(medication);

        Medication actualMedication = medicationService.save(medication);

        verify(medicationRepository).save(medication);
        assertEquals(actualMedication, medication);
    }

    @Test
    public void shouldFindAllByDroneSerialNumber() {
        Medication medication = createMedication();
        String serialNumber = "serialNumber";
        when(medicationRepository.findAllByDroneId(serialNumber)).thenReturn(Collections.singleton(medication));

        Set<Medication> actualMedication = medicationRepository.findAllByDroneId(serialNumber);

        assertEquals(actualMedication, Collections.singleton(medication));
    }
}
