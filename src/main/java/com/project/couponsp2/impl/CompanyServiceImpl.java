package com.project.couponsp2.impl;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.beans.Company;
import com.project.couponsp2.beans.Coupon;
import com.project.couponsp2.exceptions.ErrMsg;
import com.project.couponsp2.exceptions.ExceptionSystem;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.CompanyService;
import com.project.couponsp2.utils.Art;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private Integer companyID = null;

    @Override
    public boolean login(String email, String password) throws ExceptionSystem {

        if (!companyRepository.existsByEmailAndPassword(email, password))
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR, "email:" + email + " -- Password: " + password );

        companyID = companyRepository.getByEmail(email).getId();
        System.out.println( "CustomerService LoggedIn: email:" + email + " -- Password: " + password);

        return (companyID != null);

    }

    @Override
    public void addCoupon(Coupon coupon) throws ExceptionSystem {

        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (coupon.getCompany() == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.NO_COMPANY);
        if (!companyRepository.existsById(coupon.getCompany().getId()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.ID_NOT_EXISTS, "Company for Coupon: " + coupon.getCompany());
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(),coupon.getCompany().getId()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_EXISTS, "Coupon: " + coupon);

        couponRepository.saveAndFlush(coupon);
        System.out.println(Art.INSERT);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws ExceptionSystem {

        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!couponRepository.existsById(coupon.getId()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_NOT_EXISTS," Coupon: " + coupon);
        if (!couponRepository.existsByIdAndCompanyId(coupon.getId(),coupon.getCompany().getId()))
            ExceptionSystem.throwExceptionSystem(ErrMsg.UPDATE_COUPONS_COMPANY_ERR, " Coupon: " + coupon);

        couponRepository.saveAndFlush(coupon);
        System.out.println(Art.UPDATE);
    }

    @Override
    public void deleteCoupon(int couponID) throws ExceptionSystem {
        //      LogIn
        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!couponRepository.existsById(couponID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_NOT_EXISTS);
        if (!couponRepository.existsByIdAndCompanyId(couponID,companyID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COUPON_NOT_BELONG_TO_COMPANY, "Company: " + companyID + " -- Coupon: " + couponID);

        System.out.println(Art.DELETE + '\n' + "CompanyService -> Coupon: " + couponRepository.getById(couponID));
        couponRepository.deleteCustomerVsCouponByCouponId(couponID);

        System.out.println(Art.DELETE + '\n' + "CompanyService -> Coupon: " + couponRepository.getById(couponID));
        couponRepository.deleteById(couponID);
    }

    @Override
    public Company getCompanyDetails() throws ExceptionSystem {
        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);

        System.out.println(Art.SELECT);
        return companyRepository.getById(companyID);
    }

    @Override
    public List<Coupon> getCompanyCoupons() throws ExceptionSystem {
        //      LogIn
        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);

        System.out.println(Art.SELECT);
        return couponRepository.findByCompanyId(companyID);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) throws ExceptionSystem {

        //      LogIn
        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!couponRepository.existsByCompanyIdAndCategory(companyID,category))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COMPANY_DO_NOT_HAVE_CATEGORY_COUPON);

        System.out.println(Art.SELECT);
        return couponRepository.findByCompanyIdAndCategory(companyID,category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) throws ExceptionSystem {
        //      LogIn
        if (companyID == null)
            ExceptionSystem.throwExceptionSystem(ErrMsg.LOG_IN_ERROR);
        if (!couponRepository.existsByCompanyId(companyID))
            ExceptionSystem.throwExceptionSystem(ErrMsg.COMPANY_DO_NOT_HAVE_COUPONS);
        if (!couponRepository.existsByCompanyIdAndPriceLessThan(companyID,maxPrice))
            ExceptionSystem.throwExceptionSystem(ErrMsg.MISSMATCH_PRICE_FOR_COUPONS);

        System.out.println(Art.SELECT);
        return couponRepository.findByCompanyIdAndPriceLessThan(companyID,maxPrice);
    }
}
