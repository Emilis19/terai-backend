package com.academy.terai;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.academy.terai.controllers"})
@ComponentScan({"com.academy.terai.repository"})
@ComponentScan({"com.academy.terai.model"})
@ComponentScan({"com.academy.terai.service"})
@ComponentScan({"com.academy.terai.config"})
@ComponentScan({"com.academy.terai.validation"})
public class TeraiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeraiApplication.class, args);
    }

}
