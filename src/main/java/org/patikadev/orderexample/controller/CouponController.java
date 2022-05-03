package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.service.CouponService;
import org.patikadev.orderexample.service.CustomerCouponService;
import org.patikadev.orderexample.validator.implementation.CouponIDValidator;
import org.patikadev.orderexample.validator.implementation.CreateCouponValidator;
import org.patikadev.orderexample.validator.implementation.CustomerIDValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;
    private final CustomerCouponService customerCouponService;
    private final CouponIDValidator couponIDValidator;
    private final CustomerIDValidator customerIDValidator;
    private final CreateCouponValidator createCouponValidator;

    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody CreateCouponDto createCouponDto) {
        createCouponValidator.validate(createCouponDto);
        couponService.addCoupon(createCouponDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{customerId}/{couponId}")
    public ResponseEntity<?> addCouponToCustomer(@PathVariable Long customerId, @PathVariable Long couponId) {
        customerIDValidator.validate(customerId);
        couponIDValidator.validate(couponId);
        customerCouponService.addCouponToCustomer(customerId, couponId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CouponResponseDto>> getCustomerCoupons(@PathVariable Long customerId) {
        customerIDValidator.validate(customerId);
        return ResponseEntity.ok(customerCouponService.getCustomersCoupons(customerId));
    }

    @GetMapping("/{customerId}/{couponId}")
    public ResponseEntity<CouponResponseDto> getCustomerCoupon(@PathVariable Long customerId, @PathVariable Long couponId) {
        customerIDValidator.validate(customerId);
        couponIDValidator.validate(couponId);
        return ResponseEntity.ok(customerCouponService.getCustomerCoupon(customerId, couponId));
    }

    @DeleteMapping("/{customerId}/{couponId}")
    public ResponseEntity<?> deleteCouponFromCustomer(@PathVariable Long customerId, @PathVariable Long couponId) {
        customerIDValidator.validate(customerId);
        couponIDValidator.validate(couponId);
        customerCouponService.deleteCouponFromCustomer(customerId, couponId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) {
        couponIDValidator.validate(id);
        couponService.deleteCouponsById(id);
        return ResponseEntity.ok().build();
    }
}
