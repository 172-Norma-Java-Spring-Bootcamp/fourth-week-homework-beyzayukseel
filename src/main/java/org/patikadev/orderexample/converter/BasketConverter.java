package org.patikadev.orderexample.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateBasketDto;
import org.patikadev.orderexample.dto.response.BasketResponseDto;
import org.patikadev.orderexample.model.Basket;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasketConverter {
    private final CustomerDtoConverter customerDtoConverter;

    public Basket convertToEntity(CreateBasketDto createBasketDto) {
        Basket basket = new Basket();
        basket.setCustomer(customerDtoConverter.convertToCustomer(createBasketDto.customerResponseDto()));
        basket.setTotalPrice(createBasketDto.totalPrice());
        basket.setTaxPrice(createBasketDto.taxPrice());
        basket.setShippingPrice(createBasketDto.shippingPrice());
        return basket;
    }

    public CreateBasketDto convertToDto(Basket basket) {
        CreateBasketDto createBasketDto = new CreateBasketDto(
                customerDtoConverter.convertToCustomer(basket.getCustomer()),
                basket.getDiscountPrice(),
                basket.getTaxPrice(),
                basket.getShippingPrice(), basket.getTotalPrice());
        return createBasketDto;
    }

    public Basket convertToBasketEntity(BasketResponseDto basketDto) {
        Basket basket = new Basket();
        basket.setTotalPrice(basketDto.totalPrice());
        basket.setTaxPrice(basketDto.taxPrice());
        basket.setShippingPrice(basketDto.shippingPrice());
        return basket;
    }


}
