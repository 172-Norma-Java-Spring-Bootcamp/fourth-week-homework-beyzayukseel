package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.converter.CouponDtoConverter;
import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.repository.CouponRepository;
import org.patikadev.orderexample.service.CouponService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponDtoConverter couponDtoConverter;

    @Override
    public void addCoupon(CreateCouponDto createCouponDto) {
        couponRepository.save(couponDtoConverter.convertToEntity(createCouponDto));
    }

    @Override
    public CouponResponseDto getCouponById(Long couponId) {
        return couponDtoConverter.convertToResponseDto(couponRepository.findById(couponId)
                .orElseThrow(() -> new ServiceOperationException.ProductNotFoundException("Coupon not found")));
    }

    @Override
    public Boolean existsCouponById(Long couponId) {
        return couponRepository.existsById(couponId);
    }

    @Override
    public void deleteCouponsById(Long couponId) {
        if (!couponRepository.existsById(couponId)) {
            throw new ServiceOperationException.CouponNotFoundException("coupon not found");
        }
        couponRepository.deleteById(couponId);
    }
}
