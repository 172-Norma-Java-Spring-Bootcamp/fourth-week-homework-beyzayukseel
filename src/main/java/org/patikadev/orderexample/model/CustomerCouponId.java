package org.patikadev.orderexample.model;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class CustomerCouponId implements Serializable {

    private Long customerId;

    private Long couponId;

    public CustomerCouponId(Long customerId, Long couponId) {
        this.customerId = customerId;
        this.couponId = couponId;
    }

    public CustomerCouponId() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
