package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.service.CouponService;
import org.patikadev.orderexample.service.CustomerCouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;
    private final CustomerCouponService customerCouponService;

    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody CreateCouponDto createCouponDto) {
        couponService.addCoupon(createCouponDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{customerId}/{couponId}")
    public ResponseEntity<?> addCouponToCustomer(@PathVariable Long customerId, @PathVariable Long couponId) {
        customerCouponService.addCouponToCustomer(customerId, couponId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CouponResponseDto>> getCustomerCoupons(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerCouponService.getCustomersCoupons(customerId));
    }

    @GetMapping("/{customerId}/{couponId}")
    public ResponseEntity<CouponResponseDto> getCustomerCoupon(@PathVariable Long customerId, @PathVariable Long couponId) {
        return ResponseEntity.ok(customerCouponService.getCustomerCoupon(customerId, couponId));
    }

    @DeleteMapping("/{customerId}/{couponId}")
    public ResponseEntity<?> deleteCouponFromCustomer(@PathVariable Long customerId, @PathVariable Long couponId) {
        customerCouponService.deleteCouponFromCustomer(customerId, couponId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCouponById(id);
        return ResponseEntity.ok().build();
    }

}
