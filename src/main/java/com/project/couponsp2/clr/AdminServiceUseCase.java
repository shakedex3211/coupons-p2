package com.project.couponsp2.clr;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.impl.CustomerServiceImpl;
import com.project.couponsp2.repos.CouponRepository;
import com.project.couponsp2.impl.AdminServiceImpl;
import com.project.couponsp2.services.AdminService;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.login.ClientType;
import com.project.couponsp2.services.login.LoginManager;
import com.project.couponsp2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class AdminServiceUseCase implements CommandLineRunner {

//    Services
    private final LoginManager loginManager;
    private ClientService adminService;
    
    @Override
    public void run(String... args) throws Exception {

//        ** LogIn
        adminService = loginManager.login("admin@admin.com","admin", ClientType.ADMIN);
        System.out.println("Admin Loggin");

//      **------------------**
//      *** Companies Test ***
//      **------------------**
        Company company1 = Company.builder()
                .email("AdminServiceCompany1@gmail.com")
                .name("AdminCompany1")
                .password("123")
                .build();

        Company company2 = Company.builder()
                .email("AdminServiceCompany2@gmail.com")
                .name("AdminCompany2")
                .password("123")
                .build();

//        ** addCompany

        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).addCompany(company1);
        System.out.println("AdminService -> addCompany -> Company: ");
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getAllCompanies().forEach(System.out::println);

//        ** Add Array of Companies
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).addCompanies(Arrays.asList(company2));
        System.out.println("AdminService -> addCompanies -> Companies:");

        List<Company> companies = ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getAllCompanies();
        System.out.println(Art.SELECT + '\n'+ "AdminService -> getAllCompanies: " + companies.stream().count() + " Companies");
        companies.forEach(System.out::println);

//        ** Delete Company
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).deleteCompany(5);
        System.out.println("AdminService: deleteCompany 5:");
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getAllCompanies().forEach(System.out::println);

//        ** UpdateCompany
        System.out.println("AdminService: updateCompany" + '\n' + "Company Befor update: " + company1);
        company1.setName("Albi");
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).updateCompany(company1.getId(),company1);
        System.out.println("Company After update: " + ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getSingleCompany(company1.getId()));

//      **----------------------**
//      *** End Companies Test ***
//      **----------------------**

//      **------------------**
//      *** Customers Test ***
//      **------------------**

        Customer customer1 = Customer.builder()
                .email("AdminServiceCustomer1@gmail.com")
                .name("AdminCustomer1")
                .password("123")
                .build();
        Customer customer2 = Customer.builder()
                .email("AdminServiceCustomer2@gmail.com")
                .name("AdminCustomer2")
                .password("123")
                .build();

//        ** Addind Customers
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).addCustomer(customer1);
        System.out.println("AdminService -> addCustomer: " + '\n' + ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getAllCustomers());
        ((AdminServiceImpl) ((AdminServiceImpl) adminService)).addCustomers(Arrays.asList(customer2));
        System.out.println("AdminService -> addCustomers: " + '\n' + ((AdminServiceImpl) ((AdminServiceImpl) adminService)).getAllCustomers());

        Customer customer3 = Customer.builder()
                .email("BeforeUpdate@local.com")
                .name("localic")
                .password("False")
                .build();

        ((AdminServiceImpl) adminService).addCustomer(customer3);
        System.out.println("AdminService -> addCustomers: " + '\n' + ((AdminServiceImpl) adminService).getAllCustomers());

//        ** Update Customers
        System.out.println("Customer Befor Update: " + '\n' + customer3);
        customer3.setEmail("AfterUpdated@localic.co.il");
        customer3.setPassword("True");
        ((AdminServiceImpl) adminService).updateCustomer(6,customer3);
        System.out.println("Customer After Update: " + '\n' + ((AdminServiceImpl) adminService).getSingleCustomer(6));

        System.out.println("All Customers:");
        ((AdminServiceImpl) adminService).getAllCustomers().forEach(System.out::println);

        System.out.println("Delete Customer: ");
        ((AdminServiceImpl) adminService).deleteCustomer(6);
        ((AdminServiceImpl) adminService).getAllCustomers().forEach(System.out::println);

//      **----------------------**
//      *** End Customers Test ***
//      **----------------------**

    }
}
