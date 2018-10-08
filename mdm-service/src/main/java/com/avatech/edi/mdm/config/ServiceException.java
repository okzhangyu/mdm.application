package com.avatech.edi.mdm.config;

public class ServiceException extends RuntimeException{

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceException() {
    }

    public ServiceException(String code,String message){
        super();
        this.code = code;
        this.message = message;
    }
}
