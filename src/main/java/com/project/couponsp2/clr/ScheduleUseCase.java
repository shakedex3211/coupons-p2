package com.project.couponsp2.clr;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.impl.AdminServiceImpl;
import com.project.couponsp2.services.AdminService;
import com.project.couponsp2.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(5)
@RequiredArgsConstructor
public class ScheduleUseCase implements CommandLineRunner {

    private final AdminService adminService;
    private final CompanyService companyService;


    @Override
    public void run(String... args) throws Exception {

        adminService.login("admin@admin.com","admin");
        companyService.login("Habibi@gmail.com","123");

//        ************************************
//        ** Schedule Coupon test to delete **
//        ************************************
        Coupon coupon4 = Coupon.builder()
                .amount(10)
                .category(Category.ELECTRICITY)
                .description("50% on Electricity Products")
                .image("localhost:8053/images/electricity.png")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusYears(10)))
                .price(90.0)
                .title("ShoutOut - Electricityy")
                .company(companyService.getCompanyDetails())
                .build();

        companyService.addCoupon(coupon4);
    }
}
