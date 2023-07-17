package com.example.technical.controllers;

import com.example.technical.config.AppPropertiesResolver;
import com.example.technical.models.response.AirportResponse;
import com.example.technical.models.response.FlightResponse;
import com.example.technical.services.IFlightsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = CustomersRestController.class)
@EnableConfigurationProperties(value = AppPropertiesResolver.class)
@ActiveProfiles("test")
class FlightsRestControllerTest {
    @Mock
    IFlightsService flightsService;

    private List<FlightResponse> flightsResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        flightsResponse = List.of(new FlightResponse("AF1000",
                new AirportResponse("AAA", "Aaa", "Aaa"),
                new AirportResponse("AAA", "Aaa", "Aaa"),
                new BigDecimal("0.00"),
                LocalDateTime.of(2023, Month.JULY, 14, 0, 43, 17)));
    }

    @Test
    void testGetAllFlights() {
        when(flightsService.getAllFlights()).thenReturn(flightsResponse);

        //Assertions.assertEquals(List.of(new FlightResponse("flightDesignator", new AirportResponse("iataCode", "city", "country"), new AirportResponse("iataCode", "city", "country"), new BigDecimal(0), LocalDateTime.of(2023, Month.JULY, 14, 0, 27, 56))), result);
    }

    @Test
    void testGetFlightsFromOriginAndDestinationIataCodes() {
        when(flightsService.getFlightsFromOriginAndDestinationIataCodes(anyString(), anyString(), any())).thenReturn(null);

        //Assertions.assertEquals(null, null);
    }
}