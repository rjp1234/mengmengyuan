package com.mengmengyuan;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mengmengyuan.*" })
@PropertySource("classpath:application.properties")
public class MengmengyuanApplication {

    public static void main(String[] args) {
        run(MengmengyuanApplication.class, args);
    }
}
