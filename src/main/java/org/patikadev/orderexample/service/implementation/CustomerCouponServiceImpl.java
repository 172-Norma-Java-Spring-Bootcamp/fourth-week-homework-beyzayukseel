package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.converter.CouponDtoConverter;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.model.CustomerCoupon;
import org.patikadev.orderexample.model.CustomerCouponId;
import org.patikadev.orderexample.repository.CustomerCouponRepository;
import org.patikadev.orderexample.service.CouponService;
import org.patikadev.orderexample.service.CustomerCouponService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerCouponServiceImpl implements CustomerCouponService {
    private final CustomerCouponRepository customerCouponRepository;
    private final CouponService couponService;
    private final CouponDtoConverter couponDtoConverter;

    @Override
    public void addCouponToCustomer(Long customerId, Long couponId) {
        CustomerCouponId customerCouponId = new CustomerCouponId(customerId, couponId);
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.setCustomerCouponId(customerCouponId);
        customerCouponRepository.save(customerCoupon);
    }

    @Override
    public void deleteCouponFromCustomer(Long customerId, Long couponId) {
        customerCouponRepository.deleteById(new CustomerCouponId(customerId, couponId));
    }

    @Override
    public List<CouponResponseDto> getCustomersCoupons(Long customerId) {
        customerCouponRepository.getCustomersCoupons(customerId);
        return null;
    }

    @Override
    public CouponResponseDto getCustomerCoupon(Long customerId, Long couponId) {
        CustomerCoupon customerCoupon = customerCouponRepository
                .findByCustomerCoupon(customerId, couponId);

        return couponService.getCouponById(customerCoupon.customerCouponId.getCouponId());
    }
}
