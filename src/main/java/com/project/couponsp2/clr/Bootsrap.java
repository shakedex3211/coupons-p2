package com.project.couponsp2.clr;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.repos.CompanyRepository;
import com.project.couponsp2.repos.CouponRepository;
import com.project.couponsp2.repos.CustomerRepository;
import com.project.couponsp2.utils.Art;
import com.sun.xml.bind.v2.runtime.output.C14nXmlOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@Order(1)
@RequiredArgsConstructor
public class Bootsrap implements CommandLineRunner {

//    Repositories
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Art.ART);
//      *---------------------*
//      **  Repository Test  **
//      ***    Companies    ***
//      **-------------------**
        Company company1 = Company.builder()
                .email("Habibi@gmail.com")
                .name("Habibi")
                .password("123")
                .build();

        Company company2 = Company.builder()
                .email("Mabruk@gmail.com")
                .name("Mabruk")
                .password("123")
                .build();

        Company company3 = Company.builder()
                .email("Sababa@gmail.com")
                .name("Sababa")
                .password("123")
                .build();

        System.out.println(Art.INSERT);
        System.out.println("saveAllAndFlush -> 3 Companies");
        companyRepository.saveAllAndFlush(Arrays.asList(company1,company2,company3));
        System.out.println(Art.SELECT);
        System.out.println("FindAll -> Companies");
        List<Company> companies = companyRepository.findAll();
        companies.forEach(System.out::println);


//      **----------------------**
//      *** End Companies Test ***
//      **----------------------**

//      *---------------------*
//      **  Repository Test  **
//      ***     Coupons     ***
//      **-------------------**
        Coupon coupon1 = Coupon.builder()
                .amount(10)
                .category(Category.ELECTRICITY)
                .description("50% on Electricity Products")
                .image("localhost:8053/images/electricity.png")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(10)))
                .price(90.0)
                .title("ShoutOut - Electricity")
                .company(companies.get(0))
                .build();

        Coupon coupon2 = Coupon.builder()
                .amount(10)
                .category(Category.FOOD)
                .description("50% on Food Products")
                .image("localhost:8053/images/electricity.png")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(10)))
                .price(80.0)
                .title("ShoutOut - Food")
                .company(companies.get(1))
                .build();

        Coupon coupon3 = Coupon.builder()
                .amount(10)
                .category(Category.ONE_PLUS_ONE)
                .description("Owner Got CRAZY!!")
                .image("localhost:8053/images/electricity.png")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(10)))
                .price(70.0)
                .title("ShoutOut - OnePlusOne")
                .company(companies.get(2))
                .build();

        System.out.println(Art.INSERT);
        System.out.println("saveAllAndFlush -> 3 Coupons");
        couponRepository.saveAllAndFlush(Arrays.asList(coupon1,coupon2,coupon3));
        System.out.println(Art.SELECT);
        System.out.println("FindAll -> Coupons");
        List<Coupon> coupons = couponRepository.findAll();
        coupons.forEach(System.out::println);

//      **----------------------**
//      ***  End Coupons Test  ***
//      **----------------------**

//      *---------------------*
//      **  Repository Test  **
//      *** Start Customers ***
//      **-------------------**

        Customer customer1 = Customer.builder()
                .email("Bilbi@Lochlum.com")
                .name("Bilbi")
                .password("LoChlum1")
                .coupons(Arrays.asList(coupons.get(0), coupons.get(1)))
                .build();

        Customer customer2 = Customer.builder()
                .email("Shimko@Hostail.com")
                .name("Shimko")
                .password("Bolivia")
                .coupons(Arrays.asList(coupons.get(1), coupons.get(2)))
                .build();

        Customer customer3 = Customer.builder()
                .email("Kobi@JohnBryce.com")
                .name("Kobiko")
                .password("SpringAndJoy")
                .coupons(Arrays.asList(coupons.get(0), coupons.get(2)))
                .build();

        System.out.println(Art.INSERT);
        System.out.println("saveAllAndFlush -> 3 Customers");
        customerRepository.saveAllAndFlush(Arrays.asList(customer1,customer2,customer3));
        System.out.println(Art.SELECT);
        System.out.println("FindAll -> Customers");
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);

    }
}
