package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.converter.OrderItemConverter;
import org.patikadev.orderexample.dto.request.CreateOrderItemDto;
import org.patikadev.orderexample.dto.response.OrderItemResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.OrderItem;
import org.patikadev.orderexample.repository.OrderItemRepository;
import org.patikadev.orderexample.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemConverter orderItemConverter;

    @Override
    public void createOrderItem(CreateOrderItemDto createOrderItemDto) {
        orderItemRepository.save(orderItemConverter.convertToOrderItem(createOrderItemDto));
    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.OrderItemNotFoundException("Order item not found"));
        OrderItemResponseDto orderItemResponseDto = orderItemConverter.convertToResponseDto(orderItem);
        return orderItemResponseDto;
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrder_Id(orderId)
                .stream()
                .map(orderItemConverter::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrderItem(Long id) {
        if (id < 0) {
            throw new ServiceOperationException.OrderItemNotFoundException("Order item not found");
        }
        orderItemRepository.deleteById(id);
    }
}
