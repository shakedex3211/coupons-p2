package com.project.couponsp2.impl;

import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Customer;
import com.project.couponsp2.exceptions.ErrMsg;
import com.project.couponsp2.exceptions.ExceptionSystem;
import com.project.couponsp2.services.AdminService;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.utils.Art;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    private boolean isLoggedin = false;

    @Override
    public boolean login(String email, String password) {
        isLoggedin = email.equals("admin@admin.com") && password.equals("admin");
        return (isLoggedin);
    }

//    *-----------------------*
//    * -- Company Methods -- *
//    *-----------------------*
    @Override
    public void addCompany(Company company) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (companyRepository.existsByEmail(company.getEmail()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.EMAIL_EXISTS, "Company Email: " + company.getEmail());
        if (companyRepository.existsByName(company.getName()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.NAME_EXISTS, "Company Name: " + company.getName());

        companyRepository.saveAndFlush(company);
        System.out.println(Art.INSERT);
    }

    public void addCompanies(List<Company> companyList) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);

        companyList.forEach(company -> {
            try {
                addCompany(company);
            } catch (ExceptionSystem e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void deleteCompany(int companyID) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!companyRepository.existsById(companyID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, " Company ID: " + companyID);

        System.out.println(Art.DELETE);
        companyRepository.deleteById(companyID);

    }

    @Override
    public void updateCompany(int companyID, Company company) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!companyRepository.existsById(companyID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, " Company ID: " + companyID);
        if (companyRepository.countByEmail(company.getEmail()) > 1)
            ExceptionSystem.throwExceptionSystem(ErrMsg.EMAIL_EXISTS, " Company Email: " + company.getEmail());
        if (companyRepository.existsByName(company.getName()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.NAME_EXISTS, " Company Name: " + company.getName());

        companyRepository.saveAndFlush(company);
    }

    @Override
    public List<Company> getAllCompanies() throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);

        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyID) throws ExceptionSystem {
        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!companyRepository.existsById(companyID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, " Company ID: " + companyID);

        return companyRepository.getById(companyID);
    }

//    *------------------------*
//    * -- Customer Methods -- *
//    *------------------------*
    @Override
    public void addCustomer(Customer customer) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (customerRepository.existsByEmail(customer.getEmail()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.EMAIL_EXISTS, "Customer Email: " + customer.getEmail());

        System.out.println(Art.INSERT);
        customerRepository.save(customer);
    }

    public void addCustomers(List<Customer> customerList) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        customerList.forEach(customer -> {
            try {
                addCustomer(customer);
            } catch (ExceptionSystem e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void deleteCustomer(int customerID) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!customerRepository.existsById(customerID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, "Customer ID: " + customerID);

        System.out.println(Art.DELETE);
        customerRepository.deleteById(customerID);

    }

    public void deleteCoupon(int couponID) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!couponRepository.existsById(couponID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, "Coupon ID: " + couponID);

        System.out.println(Art.DELETE);
        couponRepository.deleteById(couponID);
    }

    @Override
    public void updateCustomer(int customerID, Customer customer) throws ExceptionSystem {

        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!customerRepository.existsById(customerID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, " Customer ID: " + customerID);
        if (customerRepository.countByEmail(customer.getEmail()) > 1)
            ExceptionSystem.throwExceptionSystem(ErrMsg.EMAIL_EXISTS, " Customer Email: " + customer.getEmail());

        System.out.println(Art.UPDATE);
        customerRepository.saveAndFlush(customer);

    }

    @Override
    public List<Customer> getAllCustomers() throws ExceptionSystem {
        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);

        System.out.println(Art.SELECT + '\n' + "Customers List: ");
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerID) throws ExceptionSystem {
        if (!isLoggedin)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!customerRepository.existsById(customerID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, " Customer ID: " + customerID);

        System.out.println(Art.SELECT + '\n' + " -> Customer: " + customerRepository.getById(customerID));
        return customerRepository.getById(customerID);
    }

}
