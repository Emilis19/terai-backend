package com.academy.terai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

public class TeraiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeraiApplication.class, args);
    }

}
