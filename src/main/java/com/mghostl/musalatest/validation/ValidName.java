package com.mghostl.musalatest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidNameValidator.class)
public @interface ValidName {

    String message() default "Name should contain only letters, numbers, ‘-‘, ‘_’);";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
