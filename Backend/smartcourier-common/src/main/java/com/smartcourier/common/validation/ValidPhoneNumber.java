package com.smartcourier.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that a phone number is in a valid format.
 * Accepts 10-digit numbers, optionally prefixed with country code (+91, +1, etc.).
 */
@Documented
@Constraint(validatedBy = ValidPhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {

    String message() default "Phone number must be a valid 10-digit number, optionally prefixed with country code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
