package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableAutoConfiguration
//@ComponentScan
//@Configuration // defini as classes de configuraçao
@SpringBootApplication // faz a mesma coisa que as anotações acima
public class ApplicationStarte {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarte.class,args);
    }
}
