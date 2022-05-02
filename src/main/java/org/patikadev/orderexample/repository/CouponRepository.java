package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon getCouponById(Long couponId);
}
