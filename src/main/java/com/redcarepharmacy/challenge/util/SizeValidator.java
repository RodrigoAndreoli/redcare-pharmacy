package com.redcarepharmacy.challenge.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Arrays.stream;

public class SizeValidator implements ConstraintValidator<SizeValidation, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int[] list = {10, 50, 100};
        if (value == null) return true;
        return stream(list).anyMatch(i -> i == value);
    }

}

