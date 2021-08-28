package com.project.couponsp2.services;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.exceptions.ExceptionSystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    boolean login(String email, String password) throws ExceptionSystem;

    void purchaseCoupon(Coupon coupon) throws ExceptionSystem;
    Customer getCustomerDetails() throws ExceptionSystem;
    List<Coupon> getCustomerCoupons() throws ExceptionSystem;
    List<Coupon> getCustomerCoupons(Category category) throws ExceptionSystem;
    List<Coupon> getCustomerCoupons(double maxPrice) throws ExceptionSystem;

}
