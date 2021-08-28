package com.project.couponsp2.services;

import com.project.couponsp2.exceptions.ExceptionSystem;
import com.project.couponsp2.repos.CompanyRepository;
import com.project.couponsp2.repos.CouponRepository;
import com.project.couponsp2.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    public ClientService(){}

    public abstract boolean login(String email, String password) throws ExceptionSystem;

}
