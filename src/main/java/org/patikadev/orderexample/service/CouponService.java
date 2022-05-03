package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;


public interface CouponService {

    void addCoupon(CreateCouponDto createCouponDto);

    CouponResponseDto getCouponById(Long couponId);

    void deleteCouponsById(Long couponId);

    Boolean existsCouponById(Long couponId);
}
