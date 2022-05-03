package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.dto.request.CreateProductDto;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CreateProductValidator implements Validator<CreateProductDto> {
    @Override
    public void validate(CreateProductDto createProductDto) throws BaseValidationException {
        if (Objects.isNull(createProductDto)) {
            throw new ValidationOperationException.CustomerNotValidException("Product can not be null");
        }
        if (!StringUtils.hasLength(createProductDto.brand().getName())) {
            throw new ValidationOperationException.CustomerNotValidException("Brand name can not be null or empty");
        }
        if (!StringUtils.hasLength(createProductDto.createCategoryDto().name())) {
            throw new ValidationOperationException.TaxPriceNotValidException("Category name can not be null or empty");
        }
        if (!StringUtils.hasLength(createProductDto.name())) {
            throw new ValidationOperationException.TaxPriceNotValidException("Product name can not be null or empty");
        }
        if (createProductDto.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationOperationException.ShippingPriceNotValidException("Product price should can not be smaller than zero");
        }
    }
}
