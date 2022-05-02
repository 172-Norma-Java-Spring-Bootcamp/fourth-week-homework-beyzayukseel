package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.dto.request.CreateBrandDto;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateBrandValidator implements Validator<CreateBrandDto> {

    @Override
    public void validate(CreateBrandDto createBrandDto) throws BaseValidationException {
        if (Objects.isNull(createBrandDto)) {
            throw new ValidationOperationException.BrandNotValidException("Brand can not be null or empty");
        }
        if (!(StringUtils.hasLength(createBrandDto.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Brand name can not be null or empty");
        }
    }
}
