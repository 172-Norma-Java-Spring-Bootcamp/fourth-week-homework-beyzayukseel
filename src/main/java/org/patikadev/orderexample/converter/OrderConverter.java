package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.response.OrderResponseDto;
import org.patikadev.orderexample.model.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public record OrderConverter(CustomerDtoConverter customerDtoConverter, OrderItemConverter orderItemConverter) {

    public OrderResponseDto convertToDto(Order order) {
        return new OrderResponseDto(new Date(), customerDtoConverter.convertToCustomer(order.getCustomer()),
                orderItemConverter.convertToResponseList(order.getOrderItems()), order.getShippingAddress(),
                order.getBillingAddress(), order.getPaymentMethod(), order.getPaymentInfo(),
                order.getStatus(), order.getOrderedDate(), order.getEndDate());
    }
}
