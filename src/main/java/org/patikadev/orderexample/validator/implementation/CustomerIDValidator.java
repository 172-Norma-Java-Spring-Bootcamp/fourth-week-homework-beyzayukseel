package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class CustomerIDValidator implements Validator<Long> {

    @Override
    public void validate(Long id) throws BaseValidationException {
        if (id < 0) {
            throw new ValidationOperationException.CustomerIDNotValidException("Customer ID should be greater than zero.");
        }
    }
}
