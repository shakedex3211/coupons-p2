package com.project.couponsp2.exceptions;

public class ExceptionSystem extends Exception{

    public ExceptionSystem(ErrMsg errMsg){
        super(errMsg.getDesc());
    }

    public ExceptionSystem(ErrMsg errMsg, String extraData){
        super(errMsg.getDesc() + " --> ExtraData: " + extraData);
    }

    public static void throwExceptionSystem(ErrMsg errMsg) throws ExceptionSystem {
        throw new ExceptionSystem(errMsg);
    }

    public static void throwExceptionSystem(ErrMsg errMsg, String extraData) throws ExceptionSystem {
        throw new ExceptionSystem(errMsg, extraData);
    }
}
