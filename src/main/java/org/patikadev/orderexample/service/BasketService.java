package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateBasketDto;
import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.model.Customer;

public interface BasketService {

    Customer getCustomerByBasketId(Long id);

    void addBasket(CreateBasketDto createBasketDto);

    Basket getBasketById(Long basketId);

    Basket getBasketByUserId(Long userId);

    void deleteBasket(Long basketId);
}
