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

    @Override
    public void saveOrder(Long basketId) {
        if (basketId < 0 || basketId.equals(null)) {
            throw new ServiceOperationException.BasketNotFoundException("Basket not found");
        }
        System.out.println(basketId);
        List<BasketItemResponseDto> basketItemResponseDtos = basketItemService.getItemsFromBasket(basketId);
        List<BasketItem> basketItems = basketItemResponseDtos.stream()
                .map(basketItemConverter::convertToBasketItem)
                .collect(Collectors.toList());
        System.out.println(basketItems);

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

        order.setTotalPrice(totalPrice);
        String info = order.getPaymentInfo();
        order.setPaymentInfo(info);
        order.setCustomer(basketService.getCustomerByBasketId(basketId));
        order.setOrderedDate(new Date());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (id < 0) {
            throw new ServiceOperationException.OrderNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    void checkCustomerHasCoupon(Long customerId, Date orderDate, BigDecimal totalAmount) {
        List<CouponResponseDto> couponResponseDtoList = customerCouponService.getCustomersCoupons(customerId);
        if (couponResponseDtoList.isEmpty()) {
            log.info("Customer ID{} has no coupons", customerId);
        } else {
            validCustomerCoupon(couponResponseDtoList, orderDate, totalAmount);
        }
    }

    void validCustomerCoupon(List<CouponResponseDto> couponResponseDtoList, Date orderDate, BigDecimal totalAmount) {
        List<Integer> discountAmounts = new ArrayList<>();
        for (CouponResponseDto couponResponseDto : couponResponseDtoList) {
            if (orderDate.after(couponResponseDto.startDate()) && orderDate.before(couponResponseDto.endDate())) {
                discountAmounts.add(couponResponseDto.amount());
            }
        }
        implementCouponsDiscount(discountAmounts, totalAmount);
    }

    private BigDecimal implementCouponsDiscount(List<Integer> discountAmounts, BigDecimal totalAmount) {
        for (Integer discounts : discountAmounts) {
            totalAmount = totalAmount.subtract(BigDecimal.valueOf(discounts));
        }
        return totalAmount;
    }
}
