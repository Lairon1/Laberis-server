package com.lairon.laberis;

import com.lairon.laberis.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class LaberisApplication {



    public static void main(String[] args) {
        SpringApplication.run(LaberisApplication.class, args);
    }

}
