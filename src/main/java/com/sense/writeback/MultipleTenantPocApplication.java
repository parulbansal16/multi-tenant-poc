package com.sense.writeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = "com.sense.writeback", exclude = SecurityAutoConfiguration.class)
@EnableWebFlux
public class MultipleTenantPocApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultipleTenantPocApplication.class, args);
    }
}
