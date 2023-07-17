package com.example.technical.services;

import com.example.technical.TestApplication;
import com.example.technical.mappers.FlightMapper;
import com.example.technical.models.entities.Airport;
import com.example.technical.models.entities.Flight;
import com.example.technical.models.response.AirportResponse;
import com.example.technical.models.response.FlightResponse;
import com.example.technical.repositories.FlightsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class FlightsServiceTest {

    @MockBean
    private FlightsRepository flightsRepository;

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private FlightsService flightsService;

    private List<FlightResponse> flightsResponse;

    private Flight flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flight = new Flight(0L, Short.valueOf("1000"), "AF", new BigDecimal(BigInteger.valueOf(0), 2),
                LocalDateTime.of(2023, Month.JULY, 14, 0, 43, 17),
                new Airport(0L, "AAA", "Aaa", "Aaa", new BigDecimal("0.00"), null),
                new Airport(0L, "AAA", "Aaa", "Aaa", new BigDecimal("0.00"), null));

         flightsResponse = List.of(new FlightResponse("AF1000",
                new AirportResponse("AAA", "Aaa", "Aaa"),
                new AirportResponse("AAA", "Aaa", "Aaa"),
                new BigDecimal("0.00"),
                LocalDateTime.of(2023, Month.JULY, 14, 0, 43, 17)));
    }

    @Test
    void testGetAllFlights() {
        when(flightsRepository.findAll()).thenReturn(List.of(flight));
        List<FlightResponse> result = flightsService.getAllFlights();
        Assertions.assertEquals(flightsResponse, result);
    }

    /*@Test
    void testGetFlightsFromOriginAndDestinationIataCodes() {
        when(flightsRepository.findByOriginAirport_IataCodeAndDestinationAirport_IataCodeOrderByDepartureTimeAsc(anyString(), anyString(), any())).thenReturn();

        Page<FlightResponse> result = flightsService.getFlightsFromOriginAndDestinationIataCodes("AAA", "AAA", null);
        Assertions.assertEquals(flightsResponse, result);
    }*/
}