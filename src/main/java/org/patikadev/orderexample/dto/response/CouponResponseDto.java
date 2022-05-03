package org.patikadev.orderexample.dto.response;

import java.util.Date;

public record CouponResponseDto(Long id, String title,
                                String description,
                                Date startDate,
                                Date endDate,
                                int discountAmount,
                                String code,
                                int quantity) {
}
