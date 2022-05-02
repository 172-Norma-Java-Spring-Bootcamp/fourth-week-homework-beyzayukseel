package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.response.CouponResponseDto;

import java.util.List;

public interface CustomerCouponService {
    void addCouponToCustomer(Long customerId, Long couponId);

    void deleteCouponFromCustomer(Long customerId, Long couponId);

    List<CouponResponseDto> getCustomersCoupons(Long customerId);

    CouponResponseDto getCustomerCoupon(Long customerId, Long couponId);
}
