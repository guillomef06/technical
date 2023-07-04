package com.example.technical;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 * This is the main class of our project
 * it will bootstrap Spring and enable caching
 */
@SpringBootApplication
@EnableCaching
public class TestApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    /**
     * Gets model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}