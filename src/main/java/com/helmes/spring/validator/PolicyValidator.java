package com.helmes.spring.validator;

import com.helmes.spring.model.Policy;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PolicyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Policy.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "required", "Field is required.");
    }
}
