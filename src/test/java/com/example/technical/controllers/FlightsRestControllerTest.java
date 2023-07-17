package com.example.technical.controllers;

import com.example.technical.config.AppPropertiesResolver;
import com.example.technical.models.response.AirportResponse;
import com.example.technical.models.response.FlightResponse;
import com.example.technical.services.IFlightsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FlightsRestController.class)
@EnableConfigurationProperties(value = AppPropertiesResolver.class)
@ActiveProfiles("test")
class FlightsRestControllerTest {

    @MockBean
    private IFlightsService flightsService;

    @Autowired
    private MockMvc mockMvc;

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
    void testGetAllFlights() throws Exception {
        when(flightsService.getAllFlights()).thenReturn(flightsResponse);

        mockMvc.perform(get("/api/v1/flights/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFlightsFromOriginAndDestinationIataCodes() throws Exception {
        when(flightsService.getFlightsFromOriginAndDestinationIataCodes(anyString(), anyString(), any())).thenReturn(null);

        mockMvc.perform(get("/api/v1/flights/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}