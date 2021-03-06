package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.response.BasketItemResponseDto;
import org.patikadev.orderexample.model.BasketItem;
import org.springframework.stereotype.Component;

@Component
public record BasketItemConverter(BasketConverter basketConverter, ProductDtoConverter productDtoConverter) {

    public BasketItemResponseDto convertToEntity(BasketItem basketItem) {
        BasketItemResponseDto basketItemResponseDto = new BasketItemResponseDto(
                basketItem.getId(),
                productDtoConverter.convertToDto(basketItem.getProduct()),
                basketItem.getQuantity(),
                basketItem.getPrice(),
                basketItem.getDiscountPrice(),
                basketItem.getTaxPrice(),
                basketItem.getShippingPrice()
        );
        return basketItemResponseDto;
    }

    public BasketItem convertToBasketItem(BasketItemResponseDto basketItemResponseDto) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId(basketItemResponseDto.id());
        basketItem.setProduct(productDtoConverter.convertToProductResponseEntity(basketItemResponseDto.productResponseDto()));
        basketItem.setPrice(basketItemResponseDto.price());
        basketItem.setDiscountPrice(basketItemResponseDto.discountPrice());
        basketItem.setQuantity(basketItemResponseDto.quantity());
        basketItem.setShippingPrice(basketItemResponseDto.shippingPrice());
        basketItem.setTaxPrice(basketItemResponseDto.taxPrice());
        return basketItem;
    }
}
