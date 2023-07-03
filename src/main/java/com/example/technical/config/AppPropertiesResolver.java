package com.example.technical.config;

/* FILE AppPropertiesResolver
AUTHOR Guillaume
PROJECT technical
DATE 03/07/2023 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesResolver {

    @Value("${app.minimum_age}")
    private Integer minimumAge;

    @Value("${app.country}")
    private String country;

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public String getCountry() {
        return country;
    }
}
