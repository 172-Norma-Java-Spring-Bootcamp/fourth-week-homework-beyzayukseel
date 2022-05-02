package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.converter.CouponDtoConverter;
import org.patikadev.orderexample.converter.CustomerDtoConverter;
import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.repository.CouponRepository;
import org.patikadev.orderexample.service.CouponService;
import org.patikadev.orderexample.service.CustomerService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponDtoConverter couponDtoConverter;
    private final CustomerService customerService;
    private final CustomerDtoConverter customerDtoConverter;

    @Override
    public void addCoupon(CreateCouponDto createCouponDto) {
        couponRepository.save(couponDtoConverter.convertToEntity(createCouponDto));
    }

    @Override
    public CouponResponseDto getCouponById(Long couponId) {
        return couponDtoConverter.convertToResponseDto(couponRepository.getCouponById(couponId));
    }

    @Override
    public void deleteCouponById(Long couponId) {
        couponRepository.deleteById(couponId);
    }
}
