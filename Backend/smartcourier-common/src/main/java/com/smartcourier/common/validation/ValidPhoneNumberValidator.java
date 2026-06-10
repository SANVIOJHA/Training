package com.smartcourier.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validator for {@link ValidPhoneNumber} annotation.
 * Allows null/blank (use @NotBlank separately if required).
 * Accepts formats: 1234567890, +911234567890, +1-123-456-7890
 */
public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}\\)?[-.\\s]?[0-9]{3,4}[-.\\s]?[0-9]{3,4}$"
    );

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // Allow null — use @NotBlank separately if the field is required
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return true;
        }

        return PHONE_PATTERN.matcher(phoneNumber.trim()).matches();
    }
}
