package com.example.technical.config;

import com.example.technical.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* FILE AppPropertiesResolverTest
AUTHOR Guillaume
PROJECT technical
DATE 03/07/2023 */

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class AppPropertiesResolverTest {

    @Autowired
    private AppPropertiesResolver appPropertiesResolver;

    @Test
    void testProperties() {
        assertEquals(18, appPropertiesResolver.getMinimumAge());
        assertEquals("FRANCE", appPropertiesResolver.getCountry());
        assertEquals("Europe/Paris", appPropertiesResolver.getZoneId());
    }
}
