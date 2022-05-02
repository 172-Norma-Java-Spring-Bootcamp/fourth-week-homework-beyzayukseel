package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateOrderItemDto;
import org.patikadev.orderexample.dto.response.OrderItemResponseDto;

import java.util.List;

public interface OrderItemService {
    void createOrderItem(CreateOrderItemDto createOrderItemDto);

    OrderItemResponseDto getOrderItemById(Long id);

    List<OrderItemResponseDto> getOrderItemByOrderId(Long orderId);

    void deleteOrderItem(Long id);
}
