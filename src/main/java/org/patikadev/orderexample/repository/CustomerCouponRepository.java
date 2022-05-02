package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.CustomerCoupon;
import org.patikadev.orderexample.model.CustomerCouponId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, CustomerCouponId> {

    @Query("SELECT c.customerCouponId.couponId FROM CustomerCoupon c WHERE c.customerCouponId.customerId= ?1")
    List<Long> getCustomersCoupons(Long customerId);

    @Query("SELECT c FROM CustomerCoupon c WHERE " +
            "c.customerCouponId.customerId= ?1 AND c.customerCouponId.couponId= ?2")
    CustomerCoupon findByCustomerCoupon(Long customerId, Long couponId);

}
