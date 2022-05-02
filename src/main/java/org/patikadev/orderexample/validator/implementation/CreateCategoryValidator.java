package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.dto.request.CreateCategoryDto;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCategoryValidator implements Validator<CreateCategoryDto> {
    @Override
    public void validate(CreateCategoryDto createCategoryDto) throws BaseValidationException {
        if (Objects.isNull(createCategoryDto)) {
            throw new ValidationOperationException.CategoryNotValidException("Category can not be null or empty");
        }
        if (!(StringUtils.hasLength(createCategoryDto.name()))) {
            throw new ValidationOperationException.CategoryNotValidException("Category name can not be null or empty");
        }
        if (Objects.isNull(createCategoryDto.parent())) {
            throw new ValidationOperationException.CategoryNotValidException("Category parent name can not be null or empty");
        }
    }
}
