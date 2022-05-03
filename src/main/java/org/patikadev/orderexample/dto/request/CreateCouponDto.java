package org.patikadev.orderexample.dto.request;

import java.util.Date;

public record CreateCouponDto(String title,
                              String description,
                              Date startDate,
                              Date endDate,
                              int discountAmount,
                              String code,
                              int quantity) {
}
