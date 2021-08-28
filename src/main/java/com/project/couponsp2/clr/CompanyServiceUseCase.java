package com.project.couponsp2.clr;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.impl.CompanyServiceImpl;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.CompanyService;
import com.project.couponsp2.services.login.ClientType;
import com.project.couponsp2.services.login.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Order(3)
@RequiredArgsConstructor
public class CompanyServiceUseCase implements CommandLineRunner {

    private final LoginManager loginManager;
    private ClientService companyService;

    @Override
    public void run(String... args) throws Exception {

//        ** LogIn **
        companyService = loginManager.login("Habibi@gmail.com","123", ClientType.COMPANY);
        System.out.println("Company Details:");
        ((CompanyServiceImpl) companyService).getCompanyDetails();

        Coupon coupon = Coupon.builder()
                .amount(10)
                .category(Category.ELECTRICITY)
                .description("50% on Electricity Products")
                .image("localhost:8053/images/electricity.png")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(10)))
                .price(50.0)
                .title("ShoutOut - Electricity - Added Coupon")
                .company(((CompanyServiceImpl) companyService).getCompanyDetails())
                .build();

//        ** Add Coupons **
        System.out.println("CouponService --> AddCoupon");
        ((CompanyServiceImpl) companyService).addCoupon(coupon);
        ((CompanyServiceImpl) companyService).getCompanyCoupons().forEach(System.out::println);

        coupon.setTitle("ShoutOut - Electricity - Updated Coupon");
        ((CompanyServiceImpl) companyService).updateCoupon(coupon);
        System.out.println("CouponService: Update Coupon: ");
        ((CompanyServiceImpl) companyService).getCompanyCoupons().forEach(System.out::println);

//        ** Delete Coupon
        ((CompanyServiceImpl) companyService).deleteCoupon(4);
        System.out.println("CouponService: Delete Coupon: ");
        ((CompanyServiceImpl) companyService).getCompanyCoupons().forEach(System.out::println);

        coupon.setAmount(2);
        ((CompanyServiceImpl) companyService).addCoupon(coupon);

//        ** Get Coupons
        System.out.println("CouponService: Get Company Coupons:");
        ((CompanyServiceImpl) companyService).getCompanyCoupons().forEach(System.out::println);

//        Get Coupons By Category
        System.out.println("CouponService: Get Coupon By Category: Electricity");
        ((CompanyServiceImpl) companyService).getCompanyCoupons(Category.ELECTRICITY).forEach(System.out::println);

//        Get Coupons By Price
        System.out.println("CouponService: Get Coupon By max Price: 90");
        ((CompanyServiceImpl) companyService).getCompanyCoupons(90.0).forEach(System.out::println);
    }
}
