package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateOrderItemDto;
import org.patikadev.orderexample.dto.response.OrderItemResponseDto;
import org.patikadev.orderexample.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderItemConverter {

    public OrderItem convertToOrderItem(CreateOrderItemDto createOrderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(createOrderItemDto.order());
        orderItem.setQuantity(createOrderItemDto.quantity());
        orderItem.setDeliveredDate(createOrderItemDto.deliveredDate());
        orderItem.setShippingDate(createOrderItemDto.shippingDate());
        return orderItem;
    }

    public CreateOrderItemDto convertToCreateDto(OrderItem orderItem) {
        return new CreateOrderItemDto(orderItem.getQuantity(), orderItem.getOrder(),
                orderItem.getOrderStatus(), orderItem.getShippingDate(), orderItem.getDeliveredDate());
    }

    public OrderItemResponseDto convertToResponseDto(OrderItem orderItem) {
        return new OrderItemResponseDto(orderItem.getQuantity(),
                orderItem.getOrder(), orderItem.getOrderStatus(),
                orderItem.getShippingDate(), orderItem.getDeliveredDate());
    }

    public List<OrderItemResponseDto> convertToResponseList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(o -> new OrderItemResponseDto(
                        o.getQuantity(),
                        o.getOrder(),
                        o.getOrderStatus(),
                        o.getShippingDate(),
                        o.getDeliveredDate()))
                .collect(Collectors.toList());
    }
}
