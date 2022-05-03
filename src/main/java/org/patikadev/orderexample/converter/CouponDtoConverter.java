package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCouponDto;
import org.patikadev.orderexample.dto.response.CouponResponseDto;
import org.patikadev.orderexample.model.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponDtoConverter {

    public Coupon convertToEntity(CreateCouponDto createCouponDto) {
        Coupon coupon = new Coupon();
        coupon.setDiscountAmount(createCouponDto.discountAmount());
        coupon.setDescription(createCouponDto.description());
        coupon.setStartDate(createCouponDto.startDate());
        coupon.setEndDate(createCouponDto.endDate());
        coupon.setCode(createCouponDto.code());
        coupon.setQuantity(createCouponDto.quantity());
        return coupon;
    }

    public CouponResponseDto convertToResponseDto(Coupon coupon) {
        return new CouponResponseDto(coupon.getId(), coupon.getTitle(), coupon.getDescription(),
                coupon.getStartDate(), coupon.getEndDate(), coupon.getDiscountAmount(), coupon.getCode(), coupon.getQuantity());
    }
}
