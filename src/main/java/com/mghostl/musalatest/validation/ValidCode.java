package com.mghostl.musalatest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidCodeValidator.class)
public @interface ValidCode {
    String message() default "code should contain only upperCase letters, numbers, ‘_’);";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
