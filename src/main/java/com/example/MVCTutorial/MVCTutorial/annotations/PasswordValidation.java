package com.example.MVCTutorial.MVCTutorial.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = {PasswordValidator.class}
)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Please enter a valid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
