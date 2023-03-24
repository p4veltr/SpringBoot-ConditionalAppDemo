package com.example.springbootdemo;

import com.example.springbootdemo.interfaces.DevProfile;
import com.example.springbootdemo.interfaces.ProductionProfile;
import com.example.springbootdemo.interfaces.SystemProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
