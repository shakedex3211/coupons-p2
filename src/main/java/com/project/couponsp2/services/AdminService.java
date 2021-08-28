package com.project.couponsp2.services;

import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.exceptions.ExceptionSystem;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    boolean login(String email, String password) throws ExceptionSystem;

    void addCompany(Company company) throws ExceptionSystem;
    void addCompanies(List<Company> companyList) throws ExceptionSystem;
    void deleteCompany(int companyID) throws ExceptionSystem;
    void updateCompany(int companyID, Company company) throws ExceptionSystem;

    List<Company> getAllCompanies() throws ExceptionSystem;
    Company getSingleCompany(int companyID) throws ExceptionSystem;

    void addCustomer(Customer customer) throws ExceptionSystem;
    void addCustomers(List<Customer> customerList) throws ExceptionSystem;
    void deleteCustomer(int customerID) throws ExceptionSystem;
    void updateCustomer(int customerID, Customer customer) throws ExceptionSystem;

    List<Customer> getAllCustomers() throws ExceptionSystem;
    Customer getSingleCustomer(int customerID) throws ExceptionSystem;
}
