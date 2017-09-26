package com.fly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class LookApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LookApplication.class);
        application.run(args);
        LOGGER.info("fly boot template started!!!");

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LookApplication.class);
    }
}
