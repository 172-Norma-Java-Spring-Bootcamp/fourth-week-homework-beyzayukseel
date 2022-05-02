package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.dto.request.CreateCustomerDto;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCustomerValidator implements Validator<CreateCustomerDto> {

    @Override
    public void validate(CreateCustomerDto createCustomerDto) throws BaseValidationException {
        // fail first approach.
        if (Objects.isNull(createCustomerDto)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer can not be null or empty");
        }
        if (!(StringUtils.hasLength(createCustomerDto.userName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer username can not be null or empty");
        }
        if (!(StringUtils.hasLength(createCustomerDto.password()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer password can not be null or empty");
        }
        if (Objects.isNull(createCustomerDto.identity())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer identity can not be null or empty");
        }
        if (Objects.isNull(createCustomerDto.gender())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender can not be null or empty");
        }

    }
}
