package com.project.couponsp2.clr;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.impl.CustomerServiceImpl;
import com.project.couponsp2.repos.CouponRepository;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.CustomerService;
import com.project.couponsp2.services.login.ClientType;
import com.project.couponsp2.services.login.LoginManager;
import com.project.couponsp2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(4)
@RequiredArgsConstructor
public class CustomerServiceUseCase implements CommandLineRunner {

    private final LoginManager loginManager;
    private ClientService customerService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(customerService);

//        *** LogIn Customer ID = 1 ***
        customerService = loginManager.login("Bilbi@Lochlum.com","LoChlum1", ClientType.CUSTOMER);
        System.out.println(((CustomerServiceImpl) customerService));

//        *** Purchase Coupon ***
        Customer customer = ((CustomerServiceImpl) customerService).getCustomerDetails();
        ((CustomerServiceImpl) customerService).purchaseCoupon(Coupon.builder().id(5).build());
        System.out.println(Art.INSERT + '\n' + " --> Customer: " + customer + '\n' + "*** purchaseCoupon *** --> ");
        ((CustomerServiceImpl) customerService).getCustomerCoupons().forEach(System.out::println);

//        *** Get Customer Coupons
        System.out.println("Customer Service --> getCustomerCoupons:");
        ((CustomerServiceImpl) customerService).getCustomerCoupons().forEach(System.out::println);

//        *** Get Customer Coupons By Category
        System.out.println("Customer Service --> getCustomerCoupons By Category:");
        ((CustomerServiceImpl) customerService).getCustomerCoupons(Category.ELECTRICITY).forEach(System.out::println);

//        *** Get Customer Coupons By Price
        System.out.println("Customer Service --> getCustomerCoupons By Max Price:");
        ((CustomerServiceImpl) customerService).getCustomerCoupons(90.0).forEach(System.out::println);

    }
}
