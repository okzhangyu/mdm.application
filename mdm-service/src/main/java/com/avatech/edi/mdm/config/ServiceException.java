package com.avatech.edi.mdm.config;

import com.avatech.edi.mdm.Exception.BaseException;

public class ServiceException extends BaseException {

    private String code;

    private String message;

    public ServiceException() {
    }

    public ServiceException(String code,String message){
        super(code,message);
    }
}
