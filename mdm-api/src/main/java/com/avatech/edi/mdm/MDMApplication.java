package com.avatech.edi.mdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.avatech.edi")
public class MDMApplication {

    public static void main(String args[]){
        SpringApplication.run(MDMApplication.class,args);
    }


}
