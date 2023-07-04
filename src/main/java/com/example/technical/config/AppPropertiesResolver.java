package com.example.technical.config;

/* FILE AppPropertiesResolver
AUTHOR Guillaume
PROJECT technical
DATE 03/07/2023 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This is the Configuration bean for our project
 *
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppPropertiesResolver {

    /**
     * Minimum age to be considered as an adult
     */
    private Integer minimumAge;

    /**
     * Country of residence
     */
    private String country;

    /**
     * Time zone ID
     */
    private String zoneId;
}
