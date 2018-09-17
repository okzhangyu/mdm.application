package com.avatech.edi.mdm.businessone;


public class B1Exception extends RuntimeException {

    private String code;
    private String message;



    public B1Exception() {
        super();
    }

    public B1Exception(String code,String message){
        super();
        this.code = code;
        this.message = message;
    }

    public B1Exception(String message){
        super();
        this.message = message;
    }

    public B1Exception(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }


    public B1Exception(Throwable arg0) {
        super(arg0);
    }
}
