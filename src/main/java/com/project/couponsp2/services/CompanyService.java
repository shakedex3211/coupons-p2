package com.project.couponsp2.services;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.exceptions.ExceptionSystem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    boolean login(String email, String password) throws ExceptionSystem;

    void addCoupon(Coupon coupon) throws ExceptionSystem;
    void updateCoupon(Coupon coupon) throws ExceptionSystem;
    void deleteCoupon(int couponID) throws ExceptionSystem;
    Company getCompanyDetails() throws ExceptionSystem;
    List<Coupon> getCompanyCoupons() throws ExceptionSystem;
    List<Coupon> getCompanyCoupons(Category category) throws ExceptionSystem;
    List<Coupon> getCompanyCoupons(double maxPrice) throws ExceptionSystem;

}
