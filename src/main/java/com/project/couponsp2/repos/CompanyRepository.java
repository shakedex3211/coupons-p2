package com.project.couponsp2.repos;

import com.project.couponsp2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

// Stand for DAO + DBDAO
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmailAndPassword(String email, String password);
    Company getByEmail(String email);
    long countByEmail(String email);

}
