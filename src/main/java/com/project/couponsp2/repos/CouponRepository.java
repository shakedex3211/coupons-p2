package com.project.couponsp2.repos;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;


public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    boolean existsByIdAndCompanyId(Integer couponID,Integer companyID);
    boolean existsByTitleAndCompanyId(String title, Integer companyID);
    boolean existsByCompanyIdAndCategory(Integer CompanyId, Category category);
    boolean existsByCompanyId(Integer CompanyId);
    boolean existsByCompanyIdAndPriceLessThan(Integer CompanyId, Double maxPrice);

    List<Coupon> findByCompanyId(Integer CompanyId);
    List<Coupon> findByCompanyIdAndCategory(Integer CompanyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThan(Integer CompanyId, Double maxPrice);

    List<Coupon> findByEndDateBefore(Date expDate);

    @Transactional
    @Modifying
    void deleteByEndDateBefore(Date exiredDate);

    @Query(value =
            "SELECT groupons.coupons.* FROM groupons.coupons " +
                    ", groupons.customers_coupons " +
                    "WHERE coupons.id = customers_coupons.coupons_id " +
                    "AND customers_coupons.customer_id = :customerId " +
                    "AND coupons.category = :categoryId"
            , nativeQuery = true)
    List<Coupon> findByCustomerIdAndCategory(@Param("customerId") Integer customerId, @Param("categoryId") Integer categoryID);

    @Query(value =
            "select coupons.* from groupons.coupons coupons " +
                    ", groupons.customers_coupons customers_coupons " +
                    "where coupons.id = customers_coupons.coupons_id " +
                    "and customers_coupons.customer_id = :customerId " +
                    "and coupons.price < :maxPrice"
            , nativeQuery = true)
    List<Coupon> findByCustomerIdAndPriceLessThan(@Param("customerId") Integer customerId, @Param("maxPrice") Double maxPrice);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM groupons.customers_coupons WHERE coupons_id = :CouponId", nativeQuery = true)
    void deleteCustomerVsCouponByCouponId(@Param("CouponId") Integer CouponId);

}
