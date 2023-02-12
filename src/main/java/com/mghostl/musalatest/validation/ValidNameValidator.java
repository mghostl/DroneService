package com.mghostl.musalatest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String testValue = value.toUpperCase();
        for (int i = 0; i < testValue.length(); i++) {
            char letter = testValue.charAt(i);
            if (!(letter >= 'A' && letter <= 'Z'
                    || letter >= '0' && letter <= '9'
                    || letter == '-'
                    || letter == '_')) return false;
        }
        return true;
    }
}
