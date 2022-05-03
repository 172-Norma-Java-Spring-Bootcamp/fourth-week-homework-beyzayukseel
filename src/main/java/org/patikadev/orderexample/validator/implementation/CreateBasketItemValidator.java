package org.patikadev.orderexample.validator.implementation;

import org.patikadev.orderexample.dto.request.CreateBasketItemDto;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CreateBasketItemValidator implements Validator<CreateBasketItemDto> {
    @Override
    public void validate(CreateBasketItemDto createBasketItemDto) throws BaseValidationException {
        if (Objects.isNull(createBasketItemDto)) {
            throw new ValidationOperationException.CustomerNotValidException("Basket item can not be null");
        }
        if ((createBasketItemDto.basketId() == null)) {
            throw new ValidationOperationException.CustomerNotValidException("Basket id can not be null or empty");
        }
        if (createBasketItemDto.productId() == null) {
            throw new ValidationOperationException.TaxPriceNotValidException("Product id can not be null or empty");
        }
        if (createBasketItemDto.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationOperationException.PriceNotValidException("Price should can not be smaller than zero");
        }
        if (createBasketItemDto.discountPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationOperationException.DiscountPriceNotValidException("Discount price should can not be smaller than zero");
        }
        if (createBasketItemDto.shippingPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationOperationException.ShippingPriceNotValidException("Shipping price should can not be smaller than zero");
        }
        if (createBasketItemDto.taxPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationOperationException.TaxPriceNotValidException("Tax price should can not be smaller than zero");
        }
    }
}
