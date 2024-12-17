package com.example.MVCTutorial.MVCTutorial.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = {PrimeNumberValidator.class}
)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimeNumberValidation {
    String message() default "Please enter a Prime Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
