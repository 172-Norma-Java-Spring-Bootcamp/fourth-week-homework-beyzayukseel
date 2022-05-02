package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateBasketItemDto;
import org.patikadev.orderexample.dto.response.BasketItemResponseDto;

import java.util.List;

public interface BasketItemService {

    void addItemToBasket(CreateBasketItemDto createBasketItemDto);

    List<BasketItemResponseDto> getItemsFromBasket(Long basketId);

    void deleteBasketItem(Long basketItemId);
}
