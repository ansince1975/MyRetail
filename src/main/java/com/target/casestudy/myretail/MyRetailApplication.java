package com.target.casestudy.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.target.casestudy.myretail.*")
public class MyRetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRetailApplication.class, args);
    }

}
