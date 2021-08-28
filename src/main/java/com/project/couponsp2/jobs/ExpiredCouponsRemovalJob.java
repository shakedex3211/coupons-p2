package com.project.couponsp2.jobs;

import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpiredCouponsRemovalJob {

    private final CouponRepository couponRepository;

    @Scheduled(fixedDelay = 1000*60*60*24)
    public void removeExpiredCoupons(){
        List<Coupon> expCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        expCoupons.forEach(coupon -> couponRepository.deleteCustomerVsCouponByCouponId(coupon.getId()));
        couponRepository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
    }
}
