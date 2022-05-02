package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto getOrderById(Long id);

    void saveOrder(Long basket);

    void deleteOrder(Long id);
}
