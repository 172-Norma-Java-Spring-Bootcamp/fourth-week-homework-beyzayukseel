package org.patikadev.orderexample.model;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class CustomerCoupon {
    @NotNull
    @EmbeddedId
    public CustomerCouponId customerCouponId;
}
