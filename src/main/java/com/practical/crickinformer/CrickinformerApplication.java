package com.practical.crickinformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.practical.model")
@ComponentScan(basePackages = {"com.practical",
        "com.practical.repository",
        "com.practical.service",
        "com.practical.controller",
        "com.practical.model"})
@EnableJpaRepositories(basePackages = "com.practical.repository")
public class CrickinformerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrickinformerApplication.class, args);
    }

}
