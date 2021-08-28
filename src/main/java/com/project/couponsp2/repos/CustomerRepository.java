package com.project.couponsp2.repos;

import com.project.couponsp2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);

    long countByEmail(String email);
    Customer getByEmail(String email);
}
