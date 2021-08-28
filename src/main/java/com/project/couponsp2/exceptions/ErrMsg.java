package com.project.couponsp2.exceptions;

public enum ErrMsg {
    NAME_EXISTS("Name Already Exists!"),
    EMAIL_EXISTS("Email Adress Already Exists!"),
    EMAIL_NOT_FOUND("Email Not Exists!"),
    ID_NOT_EXISTS("ID Not Exists!"),
    ZERO_COUPONS("Out of Coupons!"),
    COUPON_EXPIRED("Coupon Have been Expired!"),
    DUPLICATE_COUPON("Customer Already Have That Coupon!"),
    NOT_LOGGED_IN("You Are Not Logged In! -> Log in First!"),
    LOG_IN_ERROR("Faild to LogIn"),
    COUPON_EXISTS("Coupon Already Exists!"),
    NO_COMPANY("Coupon Do Not Have Company"),
    COUPON_NOT_EXISTS("Coupon Does Not Exists!"),
    UPDATE_COUPONS_COMPANY_ERR("Cannot Update Coupons Company"),
    COUPON_NOT_BELONG_TO_COMPANY("Cannot Delete Coupon that not belong to the company"),
    COMPANY_DO_NOT_HAVE_CATEGORY_COUPON("Company Do not Have Coupons from that Category"),
    COMPANY_DO_NOT_HAVE_COUPONS("Company Do NOT Have Coupons"),
    MISSMATCH_PRICE_FOR_COUPONS("Company DoNOT Have Coupons less then MaxPRice that PRovided"),
    CUSTOMER_NOT_EXISTS("Customer Does NOT exists!");

    private String desc;
    ErrMsg(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return ("ERR - " + desc);
    }

}
