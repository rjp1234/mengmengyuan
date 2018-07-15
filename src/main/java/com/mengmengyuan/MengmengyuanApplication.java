package com.mengmengyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = { "com.mengmengyuan.*" })
@PropertySource("classpath:application.properties")
public class MengmengyuanApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MengmengyuanApplication.class, args);
    }

    /*********
     * index入口
     * 
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Object index() {

        return "<h1>web-api project @ zjcjava 2018</h1>";
    }

}
