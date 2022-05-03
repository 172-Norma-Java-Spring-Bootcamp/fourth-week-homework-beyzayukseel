package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BasketItemConverter;
import org.patikadev.orderexample.converter.OrderConverter;
import org.patikadev.orderexample.converter.OrderItemConverter;
import org.patikadev.orderexample.dto.response.BasketItemResponseDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.dto.response.OrderResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.model.OrderItem;
import org.patikadev.orderexample.repository.OrderRepository;
import org.patikadev.orderexample.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final BasketItemService basketItemService;
    private final BasketService basketService;
    private final BasketItemConverter basketItemConverter;
    private final OrderItemConverter orderItemConverter;
    private final OrderConverter orderConverter;
    private final CustomerCouponService customerCouponService;

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.OrderNotFoundException("Order not found"));
        return orderConverter.convertToDto(order);
    }

    @Transactional
    @Override
    public void saveOrder(Long basketId) {
        if (!basketService.existBasket(basketId)) {
            throw new ServiceOperationException.BasketNotFoundException("Basket not found");
        }
        System.out.println(basketId);
        List<BasketItemResponseDto> basketItemResponseDtos = basketItemService.getItemsFromBasket(basketId);
        List<BasketItem> basketItems = basketItemResponseDtos.stream()
                .map(basketItemConverter::convertToBasketItem)
                .collect(Collectors.toList());

        //System.out.println(basketItems);

        if (basketItems.isEmpty()) {
            throw new ServiceOperationException.OrderNotFoundException("Nothing in basket");
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        for (BasketItem items : basketItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(items.getQuantity());
            orderItem.setOrder(order);
            orderRepository.save(order);
            orderItemService.createOrderItem(orderItemConverter.convertToCreateDto(orderItem));
            totalPrice = totalPrice.add(items.getProduct().getPrice()
                    .multiply(new BigDecimal(String.valueOf(items.getQuantity()))));
            basketItemService.deleteBasketItem(items.getId());
        }
        String info = order.getPaymentInfo();
        order.setPaymentInfo(info);
        order.setCustomer(basketService.getCustomerByBasketId(basketId));
        Date orderDate = new Date();
        order.setOrderedDate(orderDate);
        order.setTotalPrice(totalPrice);
        checkCustomerHasCoupon(basketService.getCustomerByBasketId(basketId).getId(), order);
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            throw new ServiceOperationException.OrderNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    void checkCustomerHasCoupon(Long customerId, Order order) {
        List<CouponResponseDto> couponResponseDtoList = customerCouponService.getCustomersCoupons(customerId);
        if (couponResponseDtoList.isEmpty()) {
            log.info("Customer ID{} has no coupons", customerId);
        } else {
            validCustomerCoupon(couponResponseDtoList, order);
        }
    }

    void validCustomerCoupon(List<CouponResponseDto> couponResponseDtoList, Order order) {
        List<Integer> discountAmounts = new ArrayList<>();
        for (CouponResponseDto couponResponseDto : couponResponseDtoList) {
            if (order.getOrderedDate().after(couponResponseDto.startDate()) && order.getOrderedDate().before(couponResponseDto.endDate())) {
                discountAmounts.add(couponResponseDto.discountAmount());
            }
        }
        implementCouponsDiscount(discountAmounts, order);
    }

    private void implementCouponsDiscount(List<Integer> discountAmounts, Order order) {
        for (Integer discounts : discountAmounts) {
            if (order.getTotalPrice().compareTo(BigDecimal.valueOf(discounts)) > 0) {
                order.setTotalPrice(order.getTotalPrice().subtract(BigDecimal.valueOf(discounts)));
            }
            System.out.println("order price is" + order.getTotalPrice());
        }
    }
}
