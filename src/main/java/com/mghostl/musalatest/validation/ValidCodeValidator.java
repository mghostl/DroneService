package com.mghostl.musalatest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCodeValidator implements ConstraintValidator<ValidCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (int i = 0; i < value.length(); i++) {
            char letter = value.charAt(i);
            if (!(letter >= 'A' && letter <= 'Z'
                    || letter >= '0' && letter <= '9'
                    || letter == '_')) return false;
        }
        return true;
    }
}
