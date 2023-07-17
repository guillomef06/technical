package com.example.technical.services;

import com.example.technical.models.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The interface Flights service.
 */
public interface IFlightsService {

    /**
     * Gets all flights.
     *
     * @return the all flights
     */
    List<FlightResponse> getAllFlights();

    /**
     * Gets flights from origin and destination iata codes.
     *
     * @param originIataCode      the origin iata code
     * @param destinationIataCode the destination iata code
     * @param page                the page
     * @return the flights from origin and destination iata codes
     */
    Page<FlightResponse> getFlightsFromOriginAndDestinationIataCodes(String originIataCode, String destinationIataCode, Pageable page);
}
