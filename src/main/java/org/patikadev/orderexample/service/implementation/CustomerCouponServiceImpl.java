package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.CustomerCoupon;
import org.patikadev.orderexample.model.CustomerCouponId;
import org.patikadev.orderexample.repository.CustomerCouponRepository;
import org.patikadev.orderexample.service.CouponService;
import org.patikadev.orderexample.service.CustomerCouponService;
import org.patikadev.orderexample.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerCouponServiceImpl implements CustomerCouponService {
    private final CustomerCouponRepository customerCouponRepository;
    private final CouponService couponService;
    private final CustomerService customerService;

    @Override
    public void addCouponToCustomer(Long customerId, Long couponId) {
        if (!customerService.existCustomerById(customerId)) {
            throw new ServiceOperationException.CustomerNotFoundException("customer not found");
        }
        if (!couponService.existsCouponById(couponId)) {
            throw new ServiceOperationException.CustomerNotFoundException("coupon not found");
        }

        CustomerCouponId customerCouponId = new CustomerCouponId(customerId, couponId);
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.setCustomerCouponId(customerCouponId);
        customerCouponRepository.save(customerCoupon);
    }

    @Override
    public void deleteCouponFromCustomer(Long customerId, Long couponId) {
        if (!customerService.existCustomerById(customerId)) {
            throw new ServiceOperationException.CustomerNotFoundException("customer not found");
        }
        if (!couponService.existsCouponById(couponId)) {
            throw new ServiceOperationException.CustomerNotFoundException("coupon not found");
        }
        customerCouponRepository.deleteById(new CustomerCouponId(customerId, couponId));
    }

    @Override
    public void deleteCouponFromCustomers(Long couponId) {
        if (!couponService.existsCouponById(couponId)) {
            throw new ServiceOperationException.CustomerNotFoundException("coupon not found");
        }
        customerCouponRepository.deleteCustomerCouponsByCouponId(couponId);
    }

    @Override
    public List<CouponResponseDto> getCustomersCoupons(Long customerId) {
        if (!customerService.existCustomerById(customerId)) {
            throw new ServiceOperationException.CustomerNotFoundException("customer not found");
        }
        List<Long> customerCoupons = customerCouponRepository.getCustomersCoupons(customerId);
        List<CouponResponseDto> couponResponseDtoList = new ArrayList<>();
        for (Long coupons : customerCoupons) {
            couponResponseDtoList.add(couponService.getCouponById(coupons));
        }
        return couponResponseDtoList;
    }

    @Override
    public CouponResponseDto getCustomerCoupon(Long customerId, Long couponId) {
        if (!customerService.existCustomerById(customerId)) {
            throw new ServiceOperationException.CustomerNotFoundException("customer not found");
        }
        if (!couponService.existsCouponById(couponId)) {
            throw new ServiceOperationException.CustomerNotFoundException("coupon not found");
        }
        CustomerCoupon customerCoupon = customerCouponRepository
                .findByCustomerCoupon(customerId, couponId);

        return couponService.getCouponById(customerCoupon.customerCouponId.getCouponId());
    }
}
