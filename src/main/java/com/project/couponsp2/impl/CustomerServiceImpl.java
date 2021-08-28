package com.project.couponsp2.impl;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.exceptions.ErrMsg;
import com.project.couponsp2.exceptions.ExceptionSystem;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.CustomerService;
import com.project.couponsp2.utils.Art;
import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private Integer customerID = null;

    @Override
    public boolean login(String email, String password) throws ExceptionSystem {
        
        if (email == null || password == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR, "Error with Email or Password");

        if (customerRepository.existsByEmailAndPassword(email,password))
            customerID = customerRepository.getByEmail(email).getId();

        System.out.println( "CustomerService LoggedIn: email:" + email + " -- Password: " + password);
        return !(customerID == null);
    }

    @Override
    @Transactional
    public void purchaseCoupon(Coupon coupon) throws ExceptionSystem {

        isLoggedIn();

        if (!couponRepository.existsById(coupon.getId()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_NOT_EXISTS);

        Coupon dbCoupon = couponRepository.getById(coupon.getId());
        Customer customer = customerRepository.getById(customerID);

        if (dbCoupon.getAmount() == 0)
            ExceptionSystem.throwExceptionSystem(ErrMsg.ZERO_COUPONS);
        if (dbCoupon.getEndDate().before(Date.valueOf(LocalDate.now())))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_EXPIRED);
        if (customer.getCoupons().contains(dbCoupon))
            ExceptionSystem.throwExceptionSystem(ErrMsg.DUPLICATE_COUPON);

        dbCoupon.setAmount(dbCoupon.getAmount()-1);
        couponRepository.saveAndFlush(dbCoupon);

        customer.getCoupons().add(dbCoupon);
        customer.setCoupons(customer.getCoupons());
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getCustomerDetails() throws ExceptionSystem {

        isLoggedIn();
        if (!customerRepository.existsById(customerID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.CUSTOMER_NOT_EXISTS);

        System.out.println(Art.SELECT );
        return customerRepository.getById(customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons() throws ExceptionSystem {
        isLoggedIn();
        if (!customerRepository.existsById(customerID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.CUSTOMER_NOT_EXISTS);

        System.out.println(Art.SELECT);
        return customerRepository.getById(customerID).getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) throws ExceptionSystem {
        isLoggedIn();
        System.out.println(Art.SELECT);
        return couponRepository.findByCustomerIdAndCategory(customerID,  category.ordinal());
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) throws ExceptionSystem {
        isLoggedIn();
        System.out.println( Art.SELECT);

        return couponRepository.findByCustomerIdAndPriceLessThan(customerID, maxPrice);
    }

    private void isLoggedIn() throws ExceptionSystem {
        if (customerID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
    }
}
