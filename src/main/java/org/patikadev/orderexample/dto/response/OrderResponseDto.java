package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.OrderStatus;

import java.util.Date;
import java.util.List;

public record OrderResponseDto(Date date, CustomerResponseDto customerResponseDto,
                               List<OrderItemResponseDto> orderItemResponseDto,
                               String shippingAddress, String billingAddress,
                               String paymentMethod, String paymentInfo,
                               OrderStatus orderStatus, Date orderedDate,
                               Date endDate) {
}
